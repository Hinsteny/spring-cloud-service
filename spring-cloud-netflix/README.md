## spring-cloud-netflix
----
<h4>spring-cloud生态下, 使用netflix提供的微服务框架作为搭建微服务系统的基础实现;</h4>


### The Componts in spring-cloud-netflix

- Eureka: Service Discovery
- Hystrix: Circuit Breaker
- Feign: Declarative REST Client
- Ribbon: Client Side Load Balancer

### Sub-Project

- [spring-cloud-netflix-eureka](https://github.com/Hinsteny/spring-cloud-service/tree/master/spring-cloud-netflix/spring-cloud-netflix-eureka): Service Discovery

- [spring-cloud-netflix-api](https://github.com/Hinsteny/spring-cloud-service/tree/master/spring-cloud-netflix/app/spring-cloud-netflix-api): service-api model
- [spring-cloud-netflix-service](https://github.com/Hinsteny/spring-cloud-service/tree/master/spring-cloud-netflix/app/spring-cloud-netflix-service): service-provider model
- [spring-cloud-netflix-biz](https://github.com/Hinsteny/spring-cloud-service/tree/master/spring-cloud-netflix/app/spring-cloud-netflix-biz): service-consumer model


### Let's Start

1. first we need to start the service registered and discover center ----- eureka;

```
# go into spring-cloud-netflix-eureka folder, run spring-boot run
mvn spring-boot:run
```

2. then start service provider ----- spring-cloud-netflix-service;

```
# go into app/spring-cloud-netflix-service folder, run spring-boot run
mvn spring-boot:run
# when the provider has started, we can visit http://127.0.0.1:8082/service?userName=Hisoka
```

3. then start service consumer ----- spring-cloud-netflix-biz;

```
# go into app/spring-cloud-netflix-biz folder, run spring-boot run
mvn spring-boot:run
# when the consumer has started, we can visit `http://127.0.0.1:8081/rest/feign/user/Hisoka` or `http://127.0.0.1:8081/rest/ribbon/user/Hisoka`
```

