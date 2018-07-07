package com.xyz.zf.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Create by liuyang
 * 2018/6/15
 */
@Data
public class OrderMasterForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 购物车信息
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
