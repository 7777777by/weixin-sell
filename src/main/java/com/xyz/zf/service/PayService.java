package com.xyz.zf.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.xyz.zf.dto.OrderMasterDto;

/**
 * 支付
 * Create by liuyang
 * 2018/6/17
 */
public interface PayService {

    PayResponse create(OrderMasterDto orderMasterDto);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderMasterDto orderMasterDto);
}
