package org.hinsteny.scn.api.facade;

import org.hinsteny.scn.api.vos.QueryUserInfoReq;
import org.hinsteny.scn.api.vos.QueryUserInfoResp;
import org.hinsteny.scn.api.vos.UserHelloReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Hinsteny
 * @version UserFacade: UserFacade 2019-07-08 14:27 All rights reserved.$
 */
@FeignClient(name = "${feign.service.spring-cloud-netflix-api}")
@RequestMapping
public interface UserFacade {

    /**
     * say hello
     * @param userName 用户名
     * @return hello message
     */
    @GetMapping("/user/hello")
    String hello(@RequestParam String userName);

    /**
     * say hello
     * @param helloVO hello-info
     * @return hello return
     */
    @GetMapping("/user/say-hello")
    String hello(@SpringQueryMap UserHelloReq helloVO);

    /**
     * query user info
     * @param queryUserInfoReq query param
     * @return user info
     */
    @PostMapping(value = "/user/info")
    QueryUserInfoResp queryUserInfo(@RequestBody QueryUserInfoReq queryUserInfoReq);

}
