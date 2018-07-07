package com.xyz.zf.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create by liuyang
 * 2018/6/18
 */
@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {
    /**
     * 微信公众平台授权url
     */
    private String weChatMpAuthorize;
    /**
     * 微信开放平台授权url
     */
    private String weChatOpenAuthorize;
    /**
     * 点餐系统
     */
    private String sell;
}
