package com.xyz.zf.service;

import com.xyz.zf.pojo.UserInfo;

/**
 * Create by liuyang
 * 2018/6/24
 */
public interface UserInfoService {
    /**
     * 注册用户
     * @param userInfo
     * @return
     */
    UserInfo registerUser(UserInfo userInfo);

    /**
     * 根据用户名查找用户
     * @return
     */
    UserInfo findByUserName(String userName);

    /**
     * 变更邮箱
     * @param email
     */
    void editUserEmail(String email);
}
