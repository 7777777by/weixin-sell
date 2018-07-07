package com.xyz.zf.service.impl;

import com.xyz.zf.repository.UserInfoRepository;
import com.xyz.zf.enums.ResultEnum;
import com.xyz.zf.exception.SellException;
import com.xyz.zf.pojo.UserInfo;
import com.xyz.zf.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by liuyang
 * 2018/6/24
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo registerUser(UserInfo userInfo) {
        // 检查用户名是否注册，一般在前端验证的时候处理，因为注册不存在高并发的情况，这里再加一层查询是不影响性能的
        if (userInfoRepository.findUserInfoByUserName(userInfo.getUserName()) != null) {
            log.error("【注册用户】 用户名已被使用， userName={}", userInfo.getUserName());
            throw new SellException(ResultEnum.USER_NAME_EXIST);
        }
        UserInfo user = userInfoRepository.save(userInfo);
        return user;
    }

    @Override
    public UserInfo findByUserName(String userName) {
        return userInfoRepository.findUserInfoByUserName(userName);
    }

    @Override
    public void editUserEmail(String email) {
        // 通过Session 获取用户信息, 这里假装从Session中获取了用户的id，后面讲解SOA面向服务架构中的单点登录系统时，修改此处代码 FIXME
        Integer id = 1;
        // 添加一些验证，比如短信验证
        userInfoRepository.updateUserEmail(id, email);
    }
}
