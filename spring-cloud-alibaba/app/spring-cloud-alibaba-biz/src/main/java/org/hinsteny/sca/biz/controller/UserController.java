package org.hinsteny.sca.biz.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.hinsteny.sca.api.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version UserController: 2019-09-04 18:50 All rights reserved.$
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "1.0.0")
    private UserFacade userFacade;

    @GetMapping("/{name}")
    public String sayHelloWorldToDubbo(@PathVariable("name") String name) {
        logger.info("sayHelloWorldToDubbo");
        return userFacade.hello(name);
    }

}
