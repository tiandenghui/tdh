package com.kuliao.horcrux.app.controller;

import com.kuliao.horcrux.app.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feignhystrix")
public class FeignHystrixController {

  @GetMapping("/feign/{id}")
  public User findById(@PathVariable Long id) {
    User user = new User();
    user.setUsername("feignfei");
    user.setId(id);
    return user;
  }

  @GetMapping("/getmsg")
  public String getMsg() {
    return "hello feignfei...";
  }

}
