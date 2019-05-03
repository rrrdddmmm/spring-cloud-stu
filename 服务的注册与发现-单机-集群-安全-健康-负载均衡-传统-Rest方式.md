****SpringCloud概述****
    
    SpringCloud是一种解决方案
    
****Eureka注册中心介绍****

    Eureka服务端
    
****Eureka客户端【服务提供者】介绍****

    提供者...
    
****Eureka消费者介绍****
    
    消费者...
    
****Eureka服务端【注册中心】部署****
    
    *单机版
    *集群版
    
******服务的注册******

    *单机版
    *集群版

****服务发现****
    
    *传统方式发现
    
    *Rest方式发现【Feign】
      1、支持springmvc注解
      2、配置支持feign注解
      3、feign支持注解可以结合feign自定义配置可以自定义feign的编码器，解码器，日志打印，拦截器，日志开启，安全验证等
      4、手动创建fegin
        
      5、满足更强大需求时可以手动创建fegin：
        1：用户微服务的接口需要登录后才能调用，并且对于相同的API，不同角色的用户有不同的行为。
        2：让电影微服务中的同一个Feign接口，使用不同的账号登录，并调用用户微服务的接口。
    
    *负载均衡发现
        Spring-Cloud负载均衡采用Ribbon,是一种客户端的负载均衡。
            Ribbon客户端实现负载均衡机制：
                1：先选择EurekaServer【注册中心】，他优先选择负载均衡少的一个Server
                2：根据用户指定的策略【默认策略：轮询】，再从Server获取到的服务注册列表中选择一个对应的地址【实现原理是什么】
        Ribbon实例配置
            1：服务提供者【Eurake客户端】配置的对外曝光微服务名称要一致，才是Ribbon选择的关键
            
            2：消费服务配置：
                server:
                  port: 80
                eureka:
                  client:
                    register-with-eureka: false #作为消费者不提供服务，不应该注册自己
                    serviceUrl:
                #      defaultZone: http://peer1:9528/eureka/
                      defaultZone: http://enodeone.com:8001/eureka/, http://enodetwo.com:8002/eureka/, http://enodethree.com:8003/eureka/ # 集群版本，需不需要配置决定着Rubbin是否对注册中心有负载均衡
                # 健康检查开启，默认开启
                management:
                  endpoint:
                    health:
                      enabled: true
                user:
                  userServiceUrl: DICTSERVICE-PROVIDER-USER
            
            3:新版本的eureka中已经集成ribbon所以新建eureka客户端程序
               之后再主类上添加：@RibbonClient(name="DICTSERVICE-PROVIDER-USER", configuration = CustomRule.class)
               第一个参数为：集群服务名称
               第二个参数为: 均衡策略
        
        Ribbon核心组件IRule
            1:根据某种特定算法从服务列表中选取某一个要访问的服务
            2:SpringCloud结合Ribbon默认的自带有七中算法
                A:RoundRobinRule:轮询(默认)
                B:RandomRule:随机
                    配置：在客户端的配置类上加上新的Bean覆盖默认的轮询
                C:AvailabilityFilteringRule
                  1.会先过滤掉多次访问而处于断路器跳闸状态的服务
                  2.对负载超过阈值的服务，然后对剩下的服务进行轮询的策略进行调用
                D:WeightedResponseTimeRule
                  1.根据平均响应时间计算所有的服务权重，响应时间越快服务权重越大，容易被选中的概率就越高
                  2.刚启动时由于信息统计不全，所有采用轮询的策略，等信息统计足够了会自动切换到WeightedResponseTimeRule策略
                E:RetryRule
                  先按照轮询策略获取服务，如果获取的服务请求失败则在规定时间内会再次进行重新获取可用的服务。
                  如果在规定时间内没有获取到可用的服务则线程终止，不再尝试获取新的可用服务。
                F:BestAvailableRule
                  1.会先过滤掉由于多次访问故障而处于熔断跳闸状态的服务，然后选择一个并发量最小的服务。
                G:ZoneAvoidanceRule
                  使用ZoneAvoidancePredicate和AvailabilityPredicate来判断是否选择某个server服务，
                  ZoneAvoidancePredicate判断判定一个zone的运行性能是否可用，剔除不可用的zone（的所有server）
                  AvailabilityPredicate用于过滤掉连接数过多的Server。
                    
        源码地址：https://github.com/Netflix/ribbon
            
        自定义配置
            1.启动类加：@RibbonClient(name="DICTSERVICE-PROVIDER-USER", configuration = CustomRule.class)
            2.在@ComponentScan扫描不上的包内添加自定义规则
                自定义CustomRandomRule继承AbstractLoadBalancerRule类重写choose方法，实现自定义规则
                在CustomRule加载规则【ribbonRule方法】
        
    *负载均衡传统或者Rest方式发现

****服务Basic安全机制****

    1、服务消费者添加security
        前端应该如何处理，对url有什么要求
        
    2、服务提供者添加security
        客户端地址如何写才能调用服务
        答：将微服务中的 eureka.client.service-url.defaultZone 改为：
        http://用户名:密码@localhost:8761/eureka/ 即可注册到需认证的Eureka Server中
        
    3、注册中心添加security
        服务提供者，服务消费者的服务地址怎么写才能在注册中心注册与发现
        答：将微服务中的 eureka.client.service-url.defaultZone 改为：
        http://用户名:密码@localhost:8761/eureka/ 即可注册到需认证的Eureka Server中
    
    4、基于feign的security可以达到权限粒度更精确，例如：如下需求
        1：用户微服务的接口需要登录后才能调用，并且对于相同的API，不同角色的用户有不同的行为。
        2：让电影微服务中的同一个Feign接口，使用不同的账号登录，并调用用户微服务的接口。
    
    5、如何区分4和1,2,3
    
    6、操作步骤
        A:添加依赖
        B:服务端yml文件中配置开启：
         spring:
           application:
             name: eureka-register-center-8003
           security:
             basic:  # 开启基于HTTP basic认证
               enabled: true
             user:
               name: root          # 用户名
               password: 123456    # 密码
        C:修改客户端的配置
          defaultZone: http://enodetwo.com:8002/eureka/, 
          http://enodeone.com:8001/eureka/ # 集群版本
          ==>
          defaultZone: http://root@123456enodetwo.com:8002/eureka/, 
          http://root@123456enodeone.com:8001/eureka/ # 集群版本
    
****服务的健康监控****
    
1
****Eureka的元数据****
    标准元数据：主机名、IP地址、端口号、状态页和健康检查等信息，这些信息都会被发布在服务注册表中，用于服务之间的调用。
    自定义元数据：自定义元数据可以使用eureka.instance.metadata-map配置，这些元数据可以在远程客户端中访问，但是一般不会改变客户端的行为，除非客户端知道该元数据的含义。
    eureka:
      client:
        serviceUrl:
    #      defaultZone: http://peer1:9528/eureka/  # 注册中心已经开启认证 单机版本
          defaultZone: http://root:123456@enodeone.com:8001/eureka/, http://root:123456@enodetwo.com:8002/eureka/, http://root:123456@enodethree.com:8003/eureka/ # 集群版本
      instance:
        prefer-ip-address: true
        metadata-map:
          #自定义元数据，key:value  都是自定义的
          mydata: rbdata我是元数据
        instanceId: ${spring.application.name}:${server.port}  # 微服务虚拟地址

****Hystrix容错处理****

 雪崩效应：
 
    基础服务的故障导致级联故障的现象，提供者不可用导致消费者不可用且将这种不可用不断扩大
 实现容错：强大的容错机制
    
    为网络请求设置超时
    
    使用断路器模式

**命名方式**：

    服务注册中心：
    com.register.center
    eureka-register-center-8001
    eureka-register-center-8001
    com.register.center8001
    
    服务提供者
    com.provider.service
    eureka-provider-service-7001
    eureka-provider-service-7001
    com.provider.service7001
    
    
    com.consume.service
    eureka-consume-service-80
    eureka-consume-service-80
    com.consume.service80
    
    com.consume.feign.service
    feign-consume-service-8080
    
    com.consume.hystrix.service
    hystrix-consume-service8081