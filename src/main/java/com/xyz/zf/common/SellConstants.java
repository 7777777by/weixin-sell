package com.xyz.zf.common;

import lombok.Getter;

/**
 * 常量类
 * Create by liuyang
 * 2018/6/3
 */
public class SellConstants {
    /** 商品状态 0：上架；1：下架 **/
    public static final Integer PRODUCT_UP = 0;
    public static final Integer PRODUCT_DOWN = 1;
    /** 订单状态 0：新订单；1：完结；2：已取消 **/
    public static final Integer ORDER_NEW = 0;
    public static final Integer ORDER_FINISHED = 1;
    public static final Integer ORDER_CANCEL = 2;
    /** 支付状态 0：等待支付；1：支付成功 **/
    public static final Integer PAY_WAIT = 0;
    public static final Integer PAY_SUCCESS = 1;
    /** Redis常量 **/
    public static final Integer EXPIRE = 7200; //过期时间2小时
    public static final String TOKEN_PREFIX = "token_%s";
    /** Cookie常量 **/
    public static final String TOKEN = "token";
}
