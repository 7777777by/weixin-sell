package com.xyz.zf.enums;

import lombok.Getter;

/**
 * Create by liuyang
 * 2018/6/17
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {
    ORDER_NEW(0, "新订单"),
    ORDER_FINISHED(1, "完结"),
    ORDER_CANCEL(2, "已取消")
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
