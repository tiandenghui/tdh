package com.kuliao.horcrux.app.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置客户端的配置文件只用bootstrap.*，最好不要再定义一个application.*
 * 配置文件加载顺序，本地bootstrap.* -> 加载获取配置中心配置文件app-profile.* -> 本地application.*
 * 重要：
 * springcloud 按照加载顺序中的数据找到便会注入到需要的属性中
 * 一旦注入，继续加载后面的配置文件时遇到重名的配置属性，是不会覆盖的
 * 建议最好不要重复定义属性，以免用的时候造成混乱
 */
@RestController
@RefreshScope
//@RefreshScope最好不要跟@Configuration在一个类上使用
public class ConfigClientController {

    private static final String template = "Hello, %s!";

    @Value("${consu:consudefault}")
    private String name;

    @GetMapping("/consu")
    public String home() {
        return String.format(template, name);
    }

}
