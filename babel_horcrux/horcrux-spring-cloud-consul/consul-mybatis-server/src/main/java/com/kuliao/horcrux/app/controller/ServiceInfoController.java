package com.kuliao.horcrux.app.controller;

import com.kuliao.horcrux.app.model.ServiceInfo;
import com.kuliao.horcrux.app.service.ServiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/serviceinfo")
public class ServiceInfoController {

    @Autowired
    ServiceInfoService serviceInfoService;

    @RequestMapping("/getserviceinfo")
    public ServiceInfo getServiceInfoByUserName(@RequestParam("userName") String userName) {
        return serviceInfoService.getServiceInfoByUserName(userName);
    }

}
