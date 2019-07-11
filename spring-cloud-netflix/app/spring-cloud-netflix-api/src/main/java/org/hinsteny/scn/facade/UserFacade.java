package org.hinsteny.scn.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
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

}
