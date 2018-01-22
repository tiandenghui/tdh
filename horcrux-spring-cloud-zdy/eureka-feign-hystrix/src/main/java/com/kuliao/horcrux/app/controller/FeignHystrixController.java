package com.kuliao.horcrux.app.controller;

import com.kuliao.horcrux.app.entity.User;
import com.kuliao.horcrux.app.feign.FeignHystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feignhystrix")
public class FeignHystrixController {

    @Autowired
    private FeignHystrixClient feignHystrixClient;

    @GetMapping("/feign/{id}")
    public User findById(@PathVariable Long id) {
        return this.feignHystrixClient.findById(id);
    }

    @GetMapping("/getmsg")
    public String findById() {
        return this.feignHystrixClient.getMsg();
    }
}
