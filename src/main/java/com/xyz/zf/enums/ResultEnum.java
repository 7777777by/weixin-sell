package com.xyz.zf.enums;

import lombok.Getter;

/**
 * Create by liuyang
 * 2018/6/10
 */
@Getter
public enum  ResultEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STORE_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_PAY_STATUS_ERROR(16, "订单支付状态不正确"),
    CART_EMPTY(17, "购物车不能为空"),
    ORDER_OWNER_ERROR(18, "该订单不属于当前用户"),
    ORDER_DETAIL_EMPTY(19, "订单详情为空"),
    WECHAT_MP_ERROR(20, "微信公众账号方面错误"),
    WXPAY_NOTIFY_MONNEY_VERIFY(21, "微信支付异步通知金额校验不通过"),
    ORDER_CANCEL_SUCCSS(22, "订单取消成功"),
    ORDER_FINISH_SUCCESS(23, "完结订单成功"),
    PRODUCT_STATUS_ERROR(24, "商品状态不正确"),
    PRODUCT_ONSALE_SUCCESS(25, "商品上架成功"),
    PRODUCT_OFFSALE_SUCCESS(26, "商品下架成功"),
    LOGIN_FAIL(27, "登录信息不正确"),
    LOGOUT_SUCCESS(28, "登出成功"),
    USER_NAME_EXIST(29, "用户名已被使用");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
