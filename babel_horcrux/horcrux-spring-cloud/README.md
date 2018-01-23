# babel-horcrux

horcrux魂器（Horcrux）是罗琳女士凭空造出来的单词。
是在《哈利·波特》系列小说中出现的名词，指藏有一个人的部分灵魂的物体。制作魂器的过程就是你把你的灵魂分裂开，将一部分藏在身体外的某个物体中。各个灵魂又是独立思考的本体。

用来表示分布式架构不仅是一套分布式管理策略，如服务发现注册、配置中心、路由、负载均衡、断路器、数据监控等，而其中的单个微服务也可独立运行。

本系统是集成spring cloud中提供的各种分布式系统基础设施

项目启动顺序：
    config-server
    eureka-registry
    其他需要的微服务

## config-server

分布式配置中心，统一管理分布式项目的配置文件，是分布式架构中非常重要的组件。
本项目的配置中心使用svn作为配置仓库。
如eureka-registry、euraka-consumer、eureka-provider的配置文件全部放到配置中心统一管理
          
- svn uri

    svn uri: https://172.16.0.25/svn/kim_project/trunk/babel-horcrux-config/

- svn auth

 username: xxxx
 password: password123
 
## 配置中心客户端与配置文件
    
   以后所有微服务项目，除了config-server，基本上都算是的配置中心客户端，其配置文件都要放在配置中心，svn库上的babel-horcrux-config中，
   以 “应用名appname-profile.yml” 命名，如 eureka-provider-dev.yml
   
-  后期开发微服务项目总共会需要添加两个配置文件
  
   1）放在配置仓库中的以“应用名appname-profile.yml”命名的文件，如eureka-provider-dev.yml，内容参考如下：
   
   spring:
     application:
       name: 微服务名称
   logging:
     level:
       root: INFO
   eureka:
     client:
       healthcheck:
         enabled: true
       serviceUrl:
         defaultZone: http://horcrux:horcrux66@localhost:8761/eureka
     instance:
       prefer-ip-address: true
       instance-id: ${spring.application.name}:${spring.cloud.client.ipAddress}:${spring.application.instance_id:${server.port}}
   
   
   2）微服务本地配置文件bootstrap.yml
   
   bootstrap.yml 需要指明配置中心地址，统一使用如下模板：
   
   spring:
     cloud:
       config:
         uri: http://localhost:9999
         profile: dev
     application:
       name: 微服务名称
   server:
     port: 实际端口

-  使用配置中心，配置客户端注意项
   
  * 配置中心客户端的配置文件建议只用bootstrap.*，最好不要再定义一个application.*
  * 配置文件加载顺序，微服务本地bootstrap.* -> 加载获取配置中心配置文件app-profile.* -> 本地application.*
  * 在本地pom文件中加入配置客户端依赖
          
          <dependency>
  			<groupId>org.springframework.cloud</groupId>
  			<artifactId>spring-cloud-starter-config</artifactId>
  		  </dependency>
  		
  ps：
  * springcloud 按照加载顺序中的数据找到便会注入到需要的属性中
  * 一旦注入，继续加载后面的配置文件时遇到重名的配置属性，是不会覆盖的
  * 建议最好不要重复定义属性，以免用的时候造成混乱
 


