package com.xyz.zf.service.impl;

import com.xyz.zf.pojo.UserInfo;
import com.xyz.zf.util.PasswordUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Create by liuyang
 * 2018/6/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceImplTest {
    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Test
    public void registerUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("admin");
        userInfo.setPassword("123456");
        userInfo.setPhone("18888888888");
        userInfo.setEmail("666@163.com");
        userInfo.setPlatform("WeChat");
        PasswordUtil.encryptPassword(userInfo);
        UserInfo result = userInfoService.registerUser(userInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void editUserEmail() {

    }
}