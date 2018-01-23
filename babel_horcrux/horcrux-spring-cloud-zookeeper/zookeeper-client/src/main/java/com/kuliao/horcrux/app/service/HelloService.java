package com.kuliao.horcrux.app.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "sayHelloFallback")
    public String sayHello(String name) {
        return restTemplate.getForObject("http://zookeeper-service/zookeeper/hello?name=" + name, String.class);
    }

    private String sayHelloFallback(String name) {
        return "service error";
    }
}
