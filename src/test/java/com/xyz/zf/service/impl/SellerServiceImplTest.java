package com.xyz.zf.service.impl;

import com.xyz.zf.pojo.SellerInfo;
import com.xyz.zf.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Create by liuyang
 * 2018/6/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {
    private static final String OPENID = "abc";
    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenId() {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenId(OPENID);
        Assert.assertEquals(OPENID, sellerInfo.getOpenid());
    }
}