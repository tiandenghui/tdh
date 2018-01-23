package com.kuliao.horcrux.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class FooService {

    @Autowired
    RestTemplate restTemplate;

    public String hello(){
        return restTemplate.getForObject("http://eureka-provider/bar/testbar",String.class);
    }

    public String hi(){
        return restTemplate.getForObject("http://eureka-provider2/bar/testbar",String.class);
    }


    public String getServiceInfoByUserName(String userName) {
        return  restTemplate.getForObject("http://eureka-mybatis/serviceinfo/getserviceinfo?userName=" + userName,String.class);}


    public String deleteByUserName(String userName){return  restTemplate.getForObject("http://eureka-mybatis/serviceinfo/deleteOneUser?userName=" + userName,String.class);}


    public String insertOneUser(String userName,String serviceName,String servicePort){
        return  restTemplate.getForObject("http://eureka-mybatis/serviceinfo/insertOneUser?userName=" + userName+"&serviceName="+serviceName+"&servicePort="+servicePort,String.class);
    }
    public String updateOneUser(String newUserName,String newServiceName,String newServicePort,String userName){
        return  restTemplate.getForObject("http://eureka-mybatis/serviceinfo/updateUser?newUserName=" + newUserName+"&newServiceName="+newServiceName+"&newServicePort="+newServicePort+"&userName="+userName,String.class);
    }
}
