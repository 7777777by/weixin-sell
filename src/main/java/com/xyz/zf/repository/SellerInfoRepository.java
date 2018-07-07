package com.xyz.zf.repository;

import com.xyz.zf.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by liuyang
 * 2018/6/18
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findSellerInfoByOpenid(String openid);
}
