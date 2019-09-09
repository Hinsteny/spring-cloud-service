## spring-cloud-alibaba
----
<h4>spring-cloud生态下, 使用dubbo提供的微服务框架作为搭建微服务系统的基础实现;</h4>


### The Componts in spring-cloud-alibaba

- Apache-Dubbo: RMI rpc Framework

### Sub-Project

- [spring-cloud-alibaba-api](https://github.com/Hinsteny/spring-cloud-service/tree/master/spring-cloud-alibaba/app/spring-cloud-alibaba-api): service-api model
- [spring-cloud-alibaba-service](https://github.com/Hinsteny/spring-cloud-service/tree/master/spring-cloud-alibaba/app/spring-cloud-alibaba-service): service-provider model
- [spring-cloud-alibaba-biz](https://github.com/Hinsteny/spring-cloud-service/tree/master/spring-cloud-alibaba/app/spring-cloud-alibaba-biz): service-consumer model


### Let's Start

1. first we need to start one zookeeper service as registered and discover center;

```
zkServer
```

2. then start service provider ----- spring-cloud-alibaba-service;

```
# go into app/spring-cloud-alibaba-service folder, run spring-boot run
mvn spring-boot:run
```

3. then start service consumer ----- spring-cloud-alibaba-biz;

```
# go into app/spring-cloud-alibaba-biz folder, run spring-boot run
mvn spring-boot:run
# when the consumer has started, we can visit `http://127.0.0.1:8081/user/Hisoka`
```

