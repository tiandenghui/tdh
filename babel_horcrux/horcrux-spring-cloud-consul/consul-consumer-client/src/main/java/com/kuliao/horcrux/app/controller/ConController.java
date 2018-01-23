package com.kuliao.horcrux.app.controller;

import com.kuliao.horcrux.app.service.ConService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ConController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConController.class);

    @Autowired
    private ConService conService;

    @Autowired
    private LoadBalancerClient loadBalanced;

    @GetMapping("/hello")
    public String SayHe() {
        ServiceInstance serviceInstance = loadBalanced.choose("consul-provider-clientlee");
        LOGGER.info(serviceInstance.getServiceId() + " : " + serviceInstance.getHost() + " : " + serviceInstance.getPort() + "\n");
        return "he";

    }


    @GetMapping("/hi")
    public String SayHello() {
        return conService.sayHi();
    }

    @GetMapping("/get")
    public String getServiceInfoByUserName(@RequestParam("userName") String userName){
        return  conService.getServiceInfoByUserName(userName);
    }

    @GetMapping("/serviceurl")
    public String serviceUrl(){
        return  conService.serviceUrl();
    }

    @GetMapping("/getserviceinfo")
    public String getServiceInfo(){
        return  conService.getServiceInfo();
    }
}
