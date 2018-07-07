package com.xyz.zf.service.impl;

import com.xyz.zf.common.SellConstants;
import com.xyz.zf.converter.OrderMaster2OrderMasterDto;
import com.xyz.zf.repository.OrderDetailRepository;
import com.xyz.zf.repository.OrderMasterRepository;
import com.xyz.zf.dto.CartDto;
import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.enums.ResultEnum;
import com.xyz.zf.exception.SellException;
import com.xyz.zf.pojo.OrderDetail;
import com.xyz.zf.pojo.OrderMaster;
import com.xyz.zf.pojo.ProductInfo;
import com.xyz.zf.service.*;
import com.xyz.zf.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by liuyang
 * 2018/6/10
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private PayService payService;
    @Autowired
    private PushMessageService pushMessageService;
    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderMasterDto create(OrderMasterDto orderMasterDto) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品（数量，价格）
        for (OrderDetail orderDetail : orderMasterDto.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDto.setOrderId(orderId);
        orderMasterDto.setOrderAmount(orderAmount);
        orderMasterDto.setOrderStatus(SellConstants.ORDER_NEW);
        orderMasterDto.setPayStatus(SellConstants.PAY_WAIT);
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        orderMasterRepository.save(orderMaster);
        //4.扣库存
        List<CartDto> cartDtoList = orderMasterDto.getOrderDetailList().stream().map(e ->
                new CartDto(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);
        //发送WebSocket消息
        webSocket.sendMessage(orderMasterDto.getOrderId());
        return orderMasterDto;
    }

    @Override
    public OrderMasterDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderMasterDto orderMasterDto = new OrderMasterDto();
        BeanUtils.copyProperties(orderMaster, orderMasterDto);
        orderMasterDto.setOrderDetailList(orderDetailList);
        return orderMasterDto;
    }

    @Override
    public Page<OrderMasterDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderMasterDto> orderMasterDtoList = OrderMaster2OrderMasterDto.convert(orderMasterPage.getContent());
        return new PageImpl<OrderMasterDto>(orderMasterDtoList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderMasterDto cancel(OrderMasterDto orderMasterDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!SellConstants.ORDER_NEW.equals(orderMasterDto.getOrderStatus())) {
            log.error("【取消订单】 订单状态不正确， orderId={}, orderStatus={}", orderMasterDto.getOrderId(), orderMasterDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDto.setOrderStatus(SellConstants.ORDER_CANCEL);
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单取消】 更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返还库存
        if (CollectionUtils.isEmpty(orderMasterDto.getOrderDetailList())) {
            log.error("【取消订单】 订单中无商品详情，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        List<CartDto> cartDtoList = orderMasterDto.getOrderDetailList().stream().map(e ->
                new CartDto(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.increaseStock(cartDtoList);
        //如果已支付，需要退款
        if (SellConstants.PAY_SUCCESS.equals(orderMasterDto.getPayStatus())) {
            payService.refund(orderMasterDto);
        }
        return orderMasterDto;
    }

    @Override
    @Transactional
    public OrderMasterDto finish(OrderMasterDto orderMasterDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!SellConstants.ORDER_NEW.equals(orderMasterDto.getOrderStatus())) {
            log.error("【完结订单】 订单状态不正确，orderId:{}, orderStatus:{}", orderMasterDto.getOrderId(), orderMasterDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDto.setOrderStatus(SellConstants.ORDER_FINISHED);
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】 更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //推送微信模板消息
        pushMessageService.orderStatus(orderMasterDto);
        return orderMasterDto;
    }

    @Override
    @Transactional
    public OrderMasterDto paid(OrderMasterDto orderMasterDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!SellConstants.ORDER_NEW.equals(orderMasterDto.getOrderStatus())) {
            log.error("【订单支付】 订单状态不正确，orderId={}, orderStatus={}", orderMasterDto.getOrderId(), orderMasterDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!SellConstants.PAY_WAIT.equals(orderMasterDto.getPayStatus())) {
            log.error("【订单支付】 订单支付状态不正确， orderMasterDto={}", orderMasterDto);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderMasterDto.setPayStatus(SellConstants.PAY_SUCCESS);
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付】 更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderMasterDto;
    }

    @Override
    public Page<OrderMasterDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderMasterDto> orderMasterDtoList = OrderMaster2OrderMasterDto.convert(orderMasterPage.getContent());
        return new PageImpl<OrderMasterDto>(orderMasterDtoList, pageable, orderMasterPage.getTotalElements());
    }
}
