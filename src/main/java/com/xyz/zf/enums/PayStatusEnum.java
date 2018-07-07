package com.xyz.zf.enums;

import lombok.Getter;

/**
 * Create by liuyang
 * 2018/6/17
 */
@Getter
public enum PayStatusEnum implements CodeEnum {
    PAY_WAIT(0, "等待支付"),
    PAY_SUCCESS(1, "支付成功");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
