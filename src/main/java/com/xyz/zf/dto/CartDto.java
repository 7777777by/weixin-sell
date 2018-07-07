package com.xyz.zf.dto;

import lombok.Data;

/**
 * 购物车
 * Create by liuyang
 * 2018/6/10
 */
@Data
public class CartDto {
    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
