package org.hinsteny.scn.biz.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.hinsteny.scn.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hinsteny
 * @version UserController: UserController 2019-07-08 14:29 All rights reserved.$
 */
@RestController
public class UserController {

    @Value("${app.service-url}")
    private String serviceUtl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/ribbon/user/{name}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String sayHelloWorldInRibbon(@PathVariable("name") String name) {
        String res = this.restTemplate.getForObject(serviceUtl + "/user/hello?userName={userName}", String.class, name);
        return res;
    }

    @GetMapping("/feign/user/{name}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String sayHelloWorldInFeign(@PathVariable("name") String name) {
        String res = userFacade.hello(name);
        return res;
    }

    public String fallbackMethod(@PathVariable("name") String name) {
        return "fallback";
    }

}
