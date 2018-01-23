package com.kuliao.horcrux.app.controller;


import com.kuliao.horcrux.app.service.FooService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
@RequestMapping("/foo")
public class FooController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooController.class);

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    FooService fooService;

    /**
     * TODO 尝试写一个通配方法，针对所有的请求进行拦截
     * 5s内请求20次失败，就直接走 hystrixFallback
     *
     * @return
     */
    @GetMapping("/hystrixcon")
    @HystrixCommand(fallbackMethod = "hystrixFallback")
    public String hystrixConsumer() {
        LOGGER.info("FooController hystrixCon........................................");
        return this.restTemplate.getForObject("http://eureka-provider2/hystrixprovider/", String.class);
    }

    public String hystrixFallback() {
        LOGGER.info("call back failed . enter hystrixFallback........................");
        return "hystrixFallback";
    }

    @GetMapping("/loadbalance")
    public String loadbalance() {
        ServiceInstance serviceInstance1 = loadBalancer.choose("eureka-provider");
        LOGGER.info(serviceInstance1.getServiceId() + " : " + serviceInstance1.getHost() + " : " + serviceInstance1.getPort() + "\n");

        ServiceInstance serviceInstance2 = loadBalancer.choose("eureka-provider2");
        LOGGER.info(serviceInstance2.getServiceId() + " : " + serviceInstance2.getHost() + " : " + serviceInstance2.getPort() + "\n");
        return "loadbalance";
    }

    @GetMapping("/hello")
    public String hello() {
        return fooService.hello();
    }

    @GetMapping("/hi")
    public String hi() {
        return fooService.hi();
    }

    @GetMapping("/get")
    public String getServiceInfoByUserName(@RequestParam("userName") String userName) {
        return fooService.getServiceInfoByUserName(userName);
    }

    @GetMapping("/delete")
    public String deleteServiceInfoByUserName(@RequestParam("userName") String userName) {
        return fooService.deleteByUserName(userName);
    }

    @GetMapping(value = "/insertUser")
    public String insertUser(@RequestParam("userName") String userName, @RequestParam("serviceName") String serviceName, @RequestParam("servicePort") String servicePort) {
        LOGGER.info(userName + serviceName + servicePort);
        return fooService.insertOneUser(userName, serviceName, servicePort);
    }

    @GetMapping(value = "/updateUser")
    public String updateUser(@RequestParam("newUserName") String newName, @RequestParam("newServiceName") String newserviceName, @RequestParam("newServicePort") String newservicePort, @RequestParam("userName") String userName) {
        LOGGER.info(newName + newserviceName + newservicePort + userName);
        return fooService.updateOneUser(newName, newserviceName, newservicePort, userName);
    }


}
