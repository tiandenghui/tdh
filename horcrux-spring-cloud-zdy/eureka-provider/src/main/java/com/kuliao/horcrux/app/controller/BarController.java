package com.kuliao.horcrux.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bar")
public class BarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarController.class);

    @GetMapping("/testbar")
    public String testBar() {
        return "testBar";
    }

    @GetMapping("/hystrixprovider")
    public String hystrixProvider() {
        LOGGER.info("BarController's hystrixPro............................");
        return "hystrixProvider";
    }

}
