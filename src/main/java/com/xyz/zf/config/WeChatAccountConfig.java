package com.xyz.zf.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Create by liuyang
 * 2018/6/16
 */
@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WeChatAccountConfig {
    /**
     * 公众平台开发者ID(AppID)
     */
    private String myAppId;

    /**
     * 公众平台开发者密码(AppSecret)
     */
    private String myAppSecret;

    /**
     * 开放平台id
     */
    private String openAppId;

    /**
     * 开放平台秘钥
     */
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户秘钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     * 微信模板id
     */
    private Map<String, String> templateId;
}

