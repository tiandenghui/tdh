# babel-horcrux-hystrix

既然Hystrix Dashboard监控单实例节点需要通过访问实例的/hystrix.stream接口来实现.

#  hystrix 实现具体的监控信息。

- 实现步骤:
      1）首先需我们需要为服务实例添加这个端点，而添加该功能的步骤也同样简单，
        只需要下面两步：
      
            在服务实例pom.xml中的dependencies节点中新增,
        
               spring-boot-starter-actuator
        
                  监控模块以开启监控相关的端点，并确保已经引入断路器的依赖
              
               spring-cloud-starter-hystrix：
              

                 <dependency>
              
                     <groupId>org.springframework.cloud</groupId>
                  
                     <artifactId>spring-cloud-starter-hystrix</artifactId>
                  
                 </dependency>
             
                 <dependency>
             
                      <groupId>org.springframework.boot</groupId>
                  
                      <artifactId>spring-boot-starter-actuator</artifactId>
                  
                 </dependency>

     2）确保在服务实例的主类中已经使用
     
                 @EnableCircuitBreaker或
              
              
                 @EnableHystrix注解，
              
              
                 开启了断路器功能。                                                                                                       
     
     3）到这里已经完成了所有的配置，我们可以在Hystrix Dashboard的首页输入
     
                 http://localhost:****/hystrix.stream，
        
        
     
                 已启动对“eureka-consumer-ribbon-hystrix”的监控，
        
        
     
                 点击“Monitor Stream”按钮