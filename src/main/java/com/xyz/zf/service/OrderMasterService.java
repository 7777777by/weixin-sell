package com.xyz.zf.service;

import com.xyz.zf.dto.OrderMasterDto;
import com.xyz.zf.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Create by liuyang
 * 2018/6/10
 */
public interface OrderMasterService {
    /**
     * 创建订单
     */
    OrderMasterDto create(OrderMasterDto orderMasterDto);

    /**
     * 查询单个订单
     */
    OrderMasterDto findOne(String orderId);

    /**
     * 查询订单列表
     */
    Page<OrderMasterDto> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderMasterDto cancel(OrderMasterDto orderMasterDto);

    /**
     * 完结订单
     */
    OrderMasterDto finish(OrderMasterDto orderMasterDto);

    /**
     * 支付订单
     */
    OrderMasterDto paid(OrderMasterDto orderMasterDto);

    /**
     * 查询订单列表
     */
    Page<OrderMasterDto> findList( Pageable pageable);
}
