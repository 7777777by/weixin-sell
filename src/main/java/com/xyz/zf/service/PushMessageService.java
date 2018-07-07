package com.xyz.zf.service;

import com.xyz.zf.dto.OrderMasterDto;

/**
 * 推送消息
 * Create by liuyang
 * 2018/6/24
 */
public interface PushMessageService {
    /**
     * 订单状态变更消息
     * @param orderMasterDto
     */
    void orderStatus(OrderMasterDto orderMasterDto);
}
