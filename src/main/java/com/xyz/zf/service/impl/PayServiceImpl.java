package com.xyz.zf.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.enums.ResultEnum;
import com.xyz.zf.exception.SellException;
import com.xyz.zf.service.OrderMasterService;
import com.xyz.zf.service.PayService;
import com.xyz.zf.util.JsonUtil;
import com.xyz.zf.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Create by liuyang
 * 2018/6/17
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 微信支付
     * @param orderMasterDto
     * @return
     */
    @Override
    public PayResponse create(OrderMasterDto orderMasterDto) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderMasterDto.getOrderId());
        payRequest.setOrderAmount(orderMasterDto.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderMasterDto.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】 发起支付，request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 发起支付，response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    /**
     * 微信异步通知
     * @param notifyData
     * @return
     */
    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人（下单人 == 支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知， payResponse={}", payResponse);
        //查询订单
        String orderId = payResponse.getOrderId();
        OrderMasterDto orderMasterDto = orderMasterService.findOne(orderId);
        if (orderMasterDto == null) {
            log.error("【微信支付】 异步通知，订单不存在，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致
        if (!MathUtil.equals(orderMasterDto.getOrderAmount().doubleValue(), payResponse.getOrderAmount())) {
            log.error("【微信支付】 异步通知，订单金额不一致， orderId={}， 微信通知金额={}， 系统金额={}", orderId, payResponse.getOrderAmount(), orderMasterDto.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONNEY_VERIFY);
        }
        //修改订单支付状态
        orderMasterService.paid(orderMasterDto);
        return payResponse;
    }

    /**
     * 退款
     * @param orderMasterDto
     */
    @Override
    public RefundResponse refund(OrderMasterDto orderMasterDto) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderMasterDto.getOrderId());
        refundRequest.setOrderAmount(orderMasterDto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 refundRequest={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 refundResponse={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
