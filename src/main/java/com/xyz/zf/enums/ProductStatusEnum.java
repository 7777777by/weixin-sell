package com.xyz.zf.enums;

import lombok.Getter;

/**
 * Create by liuyang
 * 2018/6/17
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    PRODUCT_UP(0, "上架"),
    PRODUCT_DOWN(1, "下架");

    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
