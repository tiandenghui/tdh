package com.kuliao.horcrux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistryApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(RegistryApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RegistryApplication.class);
    }
}
