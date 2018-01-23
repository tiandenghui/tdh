# babel-horcrux-zuul-gateway

在微服务架构中，需要几个基础的服务治理组件，包括服务注册与发现、服务消费、负载均衡、断路器、
智能路由、配置管理等，由这几个基础组件相互协作，共同组建了一个简单的微服务系统。
而在微服务中负责智能路由转发的就是组件Zuul，zuul默认和Ribbon结合实现了负载均衡的功能。
后期加入对服务请求的先过滤验证后路由的功能

#  zuul-gateway 实现动态路由请求到各个微服务。

    - 实现步骤：
	    1）首先需要有：
          配置中心config-server
          
          服务发现eureka-registry
		
	    2）添加maven项目微服务
	
	    Zuul-gateway 作为client注册到Eureka上 所以要一个Springboot启动类
	
	    以及yml文件  文件中除了配置注册到EurekaServer上外 还要配置具体的路由请求部分
	    ZUUL:
         ROUTES:
            API-A:
             PATH:	/API-A/**
             SERVICEID:	CONSUMER
           
        后台启动

        3）测试步骤，
            依次启动        
                  配置中心config-server
                  
                  服务发现eureka-registry
        		   
        		  网关服务 zuul-gateway
	        然后通过路由的地址去请求服务 http://localhost:5555/api-a/？
	
	使用 http://ip:port/routes 可以查看zuul代理路径


