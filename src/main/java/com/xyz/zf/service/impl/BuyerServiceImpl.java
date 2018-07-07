package com.xyz.zf.service.impl;

import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.enums.ResultEnum;
import com.xyz.zf.exception.SellException;
import com.xyz.zf.service.BuyerService;
import com.xyz.zf.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by liuyang
 * 2018/6/16
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderMasterDto findOrderMasterOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderMasterDto cancelOrder(String openid, String orderId) {
        OrderMasterDto orderMasterDto = checkOrderOwner(openid, orderId);
        if (orderMasterDto == null) {
            log.error("【取消订单】 查不到该订单， orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderMasterService.cancel(orderMasterDto);
    }

    private OrderMasterDto checkOrderOwner(String openid, String orderId) {
        OrderMasterDto orderMasterDto = orderMasterService.findOne(orderId);
        if (orderMasterDto == null) {
            return null;
        }
        if (!orderMasterDto.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单】 订单的openid不一致， openid={}, orderId={}", openid, orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderMasterDto;
    }
}
