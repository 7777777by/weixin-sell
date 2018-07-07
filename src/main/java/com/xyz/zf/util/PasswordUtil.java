package com.xyz.zf.util;

import com.xyz.zf.pojo.UserInfo;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * 密码加密工具类
 * Create by liuyang
 * 2018/6/24
 */
public class PasswordUtil {
    /**
     * 加密密码
     * @param userInfo
     */
    public static void encryptPassword(UserInfo userInfo) {
        String salt = UUID.randomUUID().toString();
        String temPassword = salt + userInfo.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
        userInfo.setSalt(salt);
        userInfo.setPassword(md5Password);
    }

    /**
     * 解密密码
     * @param userInfo
     * @param password
     */
    public static boolean decryptPassword(UserInfo userInfo, String password) {
        String temPassword = userInfo.getSalt() + password;
        String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
        return userInfo.getPassword().equals(md5Password);
    }
}
