package com.kuliao.horcrux.app.service;

import com.kuliao.horcrux.app.service.fallback.FeignFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "zookeeper-service", fallback = FeignFallback.class)
public interface ServiceFeign {

    @RequestMapping(value = "/zookeeper/hello", method = RequestMethod.GET)
    String sayHello(@RequestParam(name = "name") String name);

}
