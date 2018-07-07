package com.xyz.zf.controller;

import com.xyz.zf.config.WeChatAccountConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Create by liuyang
 * 2018/6/16
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @Autowired
    private WeChatAccountConfig weiXinConfig;

    @RequestMapping("/auth")
    public void auth(@RequestParam("code")String code) {
        log.info("进入auth方法。。。");
        log.info("code={}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + weiXinConfig.getMyAppId() + "&secret=" + weiXinConfig.getMyAppSecret() + "&code=" + code
                + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String accessToken = restTemplate.getForObject(url, String.class);
        log.info("access_token={}", accessToken);
    }
}
