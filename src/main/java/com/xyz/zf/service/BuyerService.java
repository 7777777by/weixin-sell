package com.xyz.zf.service;

import com.xyz.zf.dto.OrderMasterDto;

import java.util.List;

/**
 * 买家
 * Create by liuyang
 * 2018/6/16
 */
public interface BuyerService {
    //查询一个订单
    OrderMasterDto findOrderMasterOne(String openid, String orderId);

    //取消订单
    OrderMasterDto cancelOrder(String openid, String orderId);
}
