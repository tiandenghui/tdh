package com.kuliao.horcrux.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceApiApp {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApiApp.class, args);
    }

}
