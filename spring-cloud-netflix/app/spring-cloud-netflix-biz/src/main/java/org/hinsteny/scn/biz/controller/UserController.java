package org.hinsteny.scn.biz.controller;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.hinsteny.scn.api.facade.UserFacade;
import org.hinsteny.scn.api.vos.QueryUserInfoReq;
import org.hinsteny.scn.api.vos.QueryUserInfoResp;
import org.hinsteny.scn.api.vos.UserHelloReq;
import org.hinsteny.scn.common.context.TagRouterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author Hinsteny
 * @version UserController: UserController 2019-07-08 14:29 All rights reserved.$
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${app.service-url}")
    private String serviceUtl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserFacade userFacade;

    /** simple http get request with ribbon and feign **/
    @GetMapping("/template/user/{name}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String sayHelloWorldInTemplate(@PathVariable("name") String name) {
        String res = this.restTemplate.getForObject(serviceUtl + "/user/hello?userName={userName}", String.class, name);
        return res;
    }

    @GetMapping("/feign/user/{name}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String sayHelloWorldInFeign(@PathVariable("name") String name) {
        String res = userFacade.hello(name);
        return res;
    }

    /** little complex http get request with feign **/
    @GetMapping("/feign/user/hello")
    public String sayHellInfoInFeign(@RequestParam("userName") String userName, @RequestParam("message") String message) {
        UserHelloReq userHelloVO = new UserHelloReq();
        userHelloVO.setName(userName);
        userHelloVO.setMessage(message);
        String res = userFacade.hello(userHelloVO);
        return res;
    }

    @PostMapping("/feign/user/query")
    public QueryUserInfoResp queryUserInfoInFeign(@RequestParam("userName") String userName) {
        QueryUserInfoReq queryUserInfoReq = new QueryUserInfoReq();
        queryUserInfoReq.setName(userName);
        QueryUserInfoResp queryUserInfoResp = userFacade.queryUserInfo(queryUserInfoReq);
        logger.info("Query user info by userName: {}, get Result: {}", userName, JSON.toJSONString(queryUserInfoResp));
        return queryUserInfoResp;
    }

    /** hystrix fallback default method **/
    public String fallbackMethod(String name) {
        return "fallback for " + name;
    }

    @GetMapping("/feign/ribbon/gray")
    public void feignGrayWhile() throws Exception {
        String message = "";
        // do while
        while (true) {
            RequestContextHolder.getRequestAttributes().removeAttribute(TagRouterContext.SPRING_CLOUD_TAG_KEY,
              SCOPE_REQUEST);
            message = "[Online No Tag]" + userFacade.hello("spring-cloud");
            logger.info(message);
            RequestContextHolder.getRequestAttributes().setAttribute(TagRouterContext.SPRING_CLOUD_TAG_KEY,
              "gray", SCOPE_REQUEST);
            message = "[Online With Tag]" + userFacade.hello("spring-cloud-grey");
            logger.info(message);
            logger.info("======================================");
            Thread.sleep(1000 * 10);
        }
    }

    @GetMapping("/template/ribbon/gray")
    public void templateGrayWhile() throws Exception {
        String message = "";
        // do while
        while (true) {
            RequestContextHolder.getRequestAttributes().removeAttribute(TagRouterContext.SPRING_CLOUD_TAG_KEY,
              SCOPE_REQUEST);
            message = "[Online No Tag]" + this.sayHelloWorldInTemplate("spring-cloud");
            logger.info(message);
            RequestContextHolder.getRequestAttributes().setAttribute(TagRouterContext.SPRING_CLOUD_TAG_KEY,
              "gray", SCOPE_REQUEST);
            message = "[Online With Tag]" + this.sayHelloWorldInTemplate("spring-cloud-grey");
            logger.info(message);
            logger.info("======================================");
            Thread.sleep(1000 * 10);
        }
    }

}
