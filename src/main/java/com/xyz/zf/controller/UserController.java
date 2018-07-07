package com.xyz.zf.controller;

import com.xyz.zf.common.SellConstants;
import com.xyz.zf.config.ProjectUrlConfig;
import com.xyz.zf.enums.ResultEnum;
import com.xyz.zf.pojo.UserInfo;
import com.xyz.zf.service.UserInfoService;
import com.xyz.zf.util.CookieUtil;
import com.xyz.zf.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Create by liuyang
 * 2018/6/24
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @RequestMapping("/goLogin")
    public String goLogin() {
        return "common/login";
    }

    @RequestMapping("/login")
    public ModelAndView login(@Param("userName") String userName,
                              @Param("password") String password,
                              HttpServletResponse response,
                              Map<String, String> map) {
        UserInfo userInfo = userInfoService.findByUserName(userName);
        if (userInfo == null) {
            map.put("url", "/sell/user/goLogin");
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            return new ModelAndView("common/error", map);
        }
        if (!PasswordUtil.decryptPassword(userInfo, password)) {
            map.put("url", "/sell/user/goLogin");
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            return new ModelAndView("common/error", map);
        }
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(SellConstants.TOKEN_PREFIX, token), userName, SellConstants.EXPIRE, TimeUnit.SECONDS);
        CookieUtil.set(response, SellConstants.TOKEN, token, SellConstants.EXPIRE);
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, String> map) {
        Cookie cookie = CookieUtil.get(request, SellConstants.TOKEN);
        if (cookie != null) {
            redisTemplate.opsForValue().getOperations().delete(String.format(SellConstants.TOKEN_PREFIX, cookie.getValue()));
            CookieUtil.set(response, SellConstants.TOKEN, null, 0);
        }
        map.put("url", projectUrlConfig.getSell() + "/sell/user/goLogin");
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        return new ModelAndView("common/success", map);
    }
}
