package org.hinsteny.scn.user;

import org.hinsteny.scn.facade.UserFacade;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version UserService: UserService 2019-07-08 14:39 All rights reserved.$
 */
@RestController
public class UserService implements UserFacade {

    /**
     * say hello
     */
    @Override
    public String hello(String userName) {
        return String.format("hello, %s!", userName);
    }
}
