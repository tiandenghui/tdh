package com.kuliao.horcrux.app.feign;

import com.kuliao.horcrux.app.entity.User;
import com.kuliao.horcrux.app.feign.impl.HystrixClientFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "eureka-provider", fallbackFactory = HystrixClientFactory.class)
public interface FeignHystrixClient {

    @RequestMapping(value = "/feignhystrix/feign/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);

    @RequestMapping("/feignhystrix/getmsg")
    public String getMsg();

}
