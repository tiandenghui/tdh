package com.kuliao.horcrux.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ConsulController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsulController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/consul/pro")
    public String consulPro() {
        return "Hello consul";
    }

    @RequestMapping("/consul/services")
    public String serviceUrl() {
        List<String> list = discoveryClient.getServices();
        list.forEach(ser -> System.out.println(ser));
        LOGGER.debug("Services size:{}, instances:{}", list.size(), list);
//        List<ServiceInstance> list = discoveryClient.getInstances("STORES");
//        if (list != null && list.size() > 0) {
//            URI uri = list.get(0).getUri();
//            LOGGER.debug("path:{}, host:{}, port:{}", uri.getPath(), uri.getHost(), Integer.valueOf(uri.getPort()));
//            return uri.getPath() + uri.getHost() + uri.getPort();
//        }
        return null;
    }

}
