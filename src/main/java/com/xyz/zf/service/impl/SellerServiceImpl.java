package com.xyz.zf.service.impl;

import com.xyz.zf.repository.SellerInfoRepository;
import com.xyz.zf.pojo.SellerInfo;
import com.xyz.zf.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by liuyang
 * 2018/6/18
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openid) {
        return sellerInfoRepository.findSellerInfoByOpenid(openid);
    }
}
