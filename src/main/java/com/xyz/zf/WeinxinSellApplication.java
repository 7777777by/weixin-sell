package com.xyz.zf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.xyz.zf.dao.mapper")
@EnableCaching
public class WeinxinSellApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeinxinSellApplication.class, args);
    }
}
