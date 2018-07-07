package com.xyz.zf.controller;

import com.xyz.zf.common.SellConstants;
import com.xyz.zf.config.ProjectUrlConfig;
import com.xyz.zf.enums.ResultEnum;
import com.xyz.zf.pojo.SellerInfo;
import com.xyz.zf.service.SellerService;
import com.xyz.zf.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户
 * Create by liuyang
 * 2018/6/18
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        //1. openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenId(openid);
        if (sellerInfo == null) {
            map.put("url", "/sell/seller/order/list");
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            return new ModelAndView("common/error", map);
        }
        //2. 设置token到redis
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(SellConstants.TOKEN_PREFIX, token), openid, SellConstants.EXPIRE, TimeUnit.SECONDS);
        //3. 设置token到cookie
        CookieUtil.set(response, SellConstants.TOKEN, token, SellConstants.EXPIRE);
        //return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
        return new ModelAndView("redirect:/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        //1. 从Cookie里查询
        Cookie cookie = CookieUtil.get(request, SellConstants.TOKEN);
        if (cookie != null) {
            //2. 清除Redis
            redisTemplate.opsForValue().getOperations().delete(String.format(SellConstants.TOKEN_PREFIX, cookie.getValue()));
            //3. 清除Cookie
            CookieUtil.set(response, SellConstants.TOKEN, null, 0);
        }
        map.put("url", projectUrlConfig.getSell() + "/sell/seller/order/list");
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        return new ModelAndView("common/success", map);
    }
}
