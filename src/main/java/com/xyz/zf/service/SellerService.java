package com.xyz.zf.service;

import com.xyz.zf.pojo.SellerInfo;

/**
 * Create by liuyang
 * 2018/6/18
 */
public interface SellerService {
    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenId(String openid);
}
