# eureka-registry-server

服务注册中心 Eureka Server，会有大量的服务向它注册，负载是非常高的，在分布式系统中起着至关重要的作用。
所以 Eureka Server 需要实现高可用。

## 高可用实现

- 实现策略
    Eureka Server也相当于一个Eureka Client，所以可以启动三个Eureka Server服务，他们同时作为Eureka Client角色，
    三个之间相互注册。最终成为对等体， 3个集群化来实现高可用。
    
- 具体实现
    使用三个不同的配置文件，按照如下的规则：
    1）应用名一样，都为eureka-registry-server
    2）端口分别为：8762、8763、8764
    3）hostname分别为：peer1、peer2、peer3
    4）serviceUrl.defaultZone 为其余两个对等体的url, 如peer1的应该为 http://peer2:8763/eureka/,http://peer3:8764/eureka/
        
    最终实现见 resources下三个配置文件：
    application-peer1.yml
    application-peer2.yml
    application-peer3.yml
        
- 部署
    将三个配置文件分别跟服务注册中心服务 eureka-registry-server 部署在三台服务上。
    
    注意：
    * 部署的时候需要将application-peerX.yml名称都修改为application.yml，不然是springcloud不能识别配置文件。
	* 本系统的IP和其他两台机器的IP与peer一一对应，需要将 peer1、peer2、peer3 添加到本地域名解析列表
        1）Windows系统，目录一般在 C:\Windows\System32\drivers\etc\hosts，添加
            # localhost name resolution is handled within DNS itself.
            172.20.0.17  peer1
            172.20.0.24  peer2 
            172.20.0.34  peer3	
            #	::1             localhost
            
        2）linux系统通过：vim /etc/hosts 来添加即可