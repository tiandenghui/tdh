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

    @RequestMapping("/getuser")
    public ServiceInfo getServiceInfoByServiceName(@RequestParam("serviceName") String serviceName) {
        return serviceInfoService.getServiceInfoByUserName(serviceName);
    }

    //    172.20.0.17:7200/serviceinfo/insertServiceInfo?userName=qqq&serviceName=eee&servicePort=3333
    @RequestMapping(value = "/insertOneUser")
    public int insertOneUser(@RequestParam("userName") String userName, @RequestParam("serviceName") String serviceName, @RequestParam("servicePort") String servicePort) {
        return serviceInfoService.insertOneService(userName, serviceName, servicePort);
    }


    @RequestMapping(value = "/insertUsers")
    public int insertOneUser(ServiceInfo serviceInfo) {
        return serviceInfoService.insertServiceInfo(serviceInfo);
    }


    @RequestMapping("/deleteOneServiceInfo")
    public boolean deleteOneServiceInfoByUserName(@RequestParam("userName") String userName) {

        return serviceInfoService.deleteOneUserByUserName(userName);
    }

    @RequestMapping("/updateUser")
    public boolean updateUser(@RequestParam("newUserName") String newUserName, @RequestParam("newServiceName") String newServiceName, @RequestParam("newServicePort") String newServicePort, @RequestParam("userName") String userName) {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setUserName(newUserName);
        serviceInfo.setServiceName(newServiceName);
        serviceInfo.setServicePort(newServicePort);
        return serviceInfoService.updateOneUserByName(serviceInfo, userName);

    }

}
