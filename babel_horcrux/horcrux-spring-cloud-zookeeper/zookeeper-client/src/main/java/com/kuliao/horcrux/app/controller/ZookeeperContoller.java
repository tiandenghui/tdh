package com.kuliao.horcrux.app.controller;

import com.kuliao.horcrux.app.service.HelloService;
import com.kuliao.horcrux.app.service.ServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zookeeper")
public class ZookeeperContoller {

    @Autowired
    private HelloService helloService;

    @Autowired
    private ServiceFeign serviceFeign;

    /**
     * http://172.20.0.15:8810/zookeeper/hello?name=ooo
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello")
    public String hello(@RequestParam String name) {
        return helloService.sayHello(name);
    }

    @RequestMapping(value = "/hello2")
    public String hello2(@RequestParam String name) {
        return serviceFeign.sayHello(name);
    }
}
