package com.xyz.zf.service;

import com.xyz.zf.pojo.ProductInfo;

/**
 * Create by liuyang
 * 2018/6/30
 */
public interface SecKillService {

    String querySecKillProductInfo(String productId);

    void orderProductMockDiffUser(String productId);
}
