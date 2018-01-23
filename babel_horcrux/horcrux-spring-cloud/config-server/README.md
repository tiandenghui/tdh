## config-server

分布式配置中心，统一管理分布式项目的配置文件，是分布式架构中非常重要的组件。
本项目的配置中心使用svn作为配置仓库。
如eureka-registry、euraka-consumer、eureka-provider的配置文件全部放到配置中心统一管理
          
- svn uri

    svn uri: https://172.16.0.25/svn/kim_project/trunk/babel-horcrux-config/

- svn auth


## 使用 kafka + spring cloud bus 实现动态刷新配置文件
   kafka：消息中间件实现消息分发
   springspring cloud bus：整合 java的事件处理机制和消息中间件消息的发送和接受，主要由发送端、接收端和事件组成
   zookeeper：是一个分布式的、分层级的文件系统，能促进客户端间的松耦合，并提供最终一致的，类似于传统文件系统中文件和目录的Znode视图。
   ZooKeeper用于管理、协调Kafka代理。每个Kafka代理都通过ZooKeeper协调其它Kafka代理。当Kafka系统中新增了代理或者某个代理故障失效时，
   ZooKeeper服务将通知生产者和消费者。生产者和消费者据此开始与其它代理协调工作。

- 实现步骤

 1 代码实现
    
    1)在config-server的application.yml 
        添加 #kafka
        spring.cloud.stream.kafka.binder.zk-nodes: 172.16.0.27:2181  #zookeeper地址
        spring.cloud.stream.kafka.binder.brokers: 172.16.0.27:9092   #kafka地址
        
    2)在config-client的在配置中心上的配置文件中添加.yml
        添加 #kafka
        spring.cloud.stream.kafka.binder.zk-nodes: 172.16.0.27:2181  #zookeeper地址
        spring.cloud.stream.kafka.binder.brokers: 172.16.0.27:9092   #kafka地址
        spring.cloud.bus.trace.enabled: true                         # 开启消息跟踪
    
 2 linux安装
    
    
  1）安装kafka、zookeeper，下载解压即可
  
  		tar -zxf zookeeper-3.4.11.tar.gz
  		tar -zxf kafka_2.12-1.0.0.tgz
  		
 2）启动kafka、zookeeper
  
   * zookeeper的启动命令：通过Xshell登陆172.16.0.27，用户名horcrux，密码hor123
  	
    tools/zookeeper-3.4.11/bin/zkServer.sh start
      
   * 在kafka的启动命令：注意，kafka普通启动会跟着关闭连接客户端而关闭。需要和zookeeper一样，后台服务跑，需要后台命令启动
  	
    tools/kafka_2.12-1.0.0/bin/kafka-server-start.sh  tools/kafka_2.12-1.0.0/config/server.properties
      
   * 后台启动命令
    
    tools/kafka_2.12-1.0.0/bin/kafka-server-start.sh -daemon tools/kafka_2.12-1.0.0/config/server.properties 1>/dev/null 2>&1 & 
     
  
  3）查看kafka、zookeeper
  
   * 查看zookeeper
     
    ps -aux|grep zookeeper  会出现zookeeper详细的一些服务
  
   * 查看kafka
   
    ps -aux|grep kafka  会出现kafak详细的一些服务
     
  4）关闭命令
  
   * kafka关闭命令：
    
    tools/kafka_2.12-1.0.0/bin/kafka-server-stop.sh tools/kafka_2.12-1.0.0/config/server.properties
  
   * zookeeper的关闭命令：
    
    tools/zookeeper-3.4.11/bin/zkServer.sh stop  
   
   5）配置客户端需要引入
   
    <dependency> spring cloud 集成bus
   	 <groupId>org.springframework.cloud</groupId>
   	 <artifactId>spring-cloud-starter-bus-kafka</artifactId>
    </dependency>
    
    <dependency> 配置中心刷新
   	 <groupId>org.springframework.boot</groupId>
   	 <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
   
   6）测试步骤
   
   * 首先启动zookeeper
   * 在启动kafka
   * 启动config-server
   * 启动config-client两个
   * 两个服务调用config-client获取配置中心config-client配置文件的某个属性，查看属性
   * 修改配置文件的属性值
   * 两个服务调用config-client获取配置中心config-client配置文件的某个属性，查看属性值和上次的值是否是最新值
   * 执行配置文件刷新命令 curl -X POST http://localhost:9989/bus/refresh 
   * 两个服务调用config-client获取配置中心config-client配置文件的某个属性，查看属性值和上次的值是否是最新值
   * 看到两个属性文件都是最新的值，证明动态刷新配置文件成功
   
  ------------
  #Config server 高可用实现
    
  ##基本原理
    1.可以开启多个配置中心服务端， 连接到一个存储仓库；
    2.将配置中心服务端作为服务提供者注册到Eureka，把配置中心客户端作为服务消费者注册到Eureka；
    3.当其中一个配置中心服务端不可用时，Eureka会将该服务剔除，并将客户端的请求分流到其他可用配置中心服务端上；
	  当多个配置客户端连接配置服务时，Eureka服务对配置客户端进行负载均衡，将其分流到不同的配置中心服务端；
      这样就实现了配置中心服务的高可用
  
  ##实现
    1.配置中心服务端改造：
       1)pom.xml中添加依赖：
            <dependency>
       			<groupId>org.springframework.cloud</groupId>
       			<artifactId>spring-cloud-starter-eureka</artifactId>
       		</dependency>
       2)在application.yml中添加eureka.client.service-url.defaultZone参数，值为注册中心的地址列表
       3)在启动类上添加注解@EnableDiscoveryClient，开启服务注册发现。
       
  ##部署
    1.规划三个配置中心服务：
       端口号：9991， 9992， 9993.
       三个配置文件模板：application-peer1.yml, application-peer2.yml,  application-peer3.yml
       部署的时候需要将application-peerX.yml名称都修改为application.yml
       
       - 注意：
       1）三个配置中心服务集群分别要部署到是三个注册中心集群高可用的机器上，
       2）注册中心的ip与配置中心的端口一一对应（地址对照配置在horcrux-spring-cloud/bootstrap.yml）
       3）配置中心服务peer1、peer2、peer3与注册中心peer1、peer2、peer3部署顺序要一致，如：
       配置中心-peer1 ===> 注册中心-peer1 
       配置中心-peer2 ===> 注册中心-peer2 
       配置中心-peer3 ===> 注册中心-peer3 
       
       
  ##测试
    1.启动注册中心
    2.启动三个配置中心服务端，等待三个配置中心服务端都注册到注册中心上
    3.启动配置中心客户端，通过客户端日志，发现客户端通过一个配置中心服务端获取自身配置
    4.停止客户端连接的配置中信服务端，重新启动配置中心客户端
    5.观测配置中心客户端，发现客户端获取配置源已经切换到其他配置中心服务端。
   
   ######注意
   使用配置中心单节点测试的时候需要注意：
   - 注释掉 bootstrap.yml 中的语句，否则默认加载的是bootstrap.yml，bootstrap.yml中的使用的是高可用的库
   - 并且注释掉 spring-cloud-starter-eureka 依赖，reimport pom.xml
     
     
   
  
  
  
  	


