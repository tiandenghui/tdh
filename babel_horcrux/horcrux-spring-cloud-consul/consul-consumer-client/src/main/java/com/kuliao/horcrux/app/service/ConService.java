package com.kuliao.horcrux.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancer;

    public String serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("consul-mybatis-server");
        if (list != null && list.size() > 0 ) {
            return list.get(0).getUri().getHost();
        }
        return null;
    }

    public String sayHi() {
        return restTemplate.getForObject("http://consul-provider-clientlee/haha", String.class);
    }

    public String getServiceInfoByUserName(String userName) {
        return restTemplate.getForObject("http://consul-mybatis-server/serviceinfo/getserviceinfo?userName=" + userName, String.class);
    }

}
