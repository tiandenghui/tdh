package com.kuliao.horcrux.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zookeeper")
public class ZookeeperController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperController.class);

    /**
     * http://172.20.0.15:8800/zookeeper/hello?name=ooo
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(@RequestParam(name = "name") String name) {
        LOGGER.info("param:name->{}", name);
        return "hello: " + name;
    }
}
