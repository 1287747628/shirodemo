package com.example.shirodemo;

import cn.dev33.satoken.SaTokenManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShirodemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirodemoApplication.class, args);
        System.out.println("sa-token init success" + SaTokenManager.getConfig());
    }

}
