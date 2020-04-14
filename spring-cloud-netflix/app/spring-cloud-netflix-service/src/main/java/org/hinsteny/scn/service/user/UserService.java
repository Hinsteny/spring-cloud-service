package org.hinsteny.scn.service.user;

import org.hinsteny.scn.api.facade.UserFacade;
import org.hinsteny.scn.api.vos.QueryUserInfoReq;
import org.hinsteny.scn.api.vos.QueryUserInfoResp;
import org.hinsteny.scn.api.vos.UserHelloReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version UserService: UserService 2019-07-08 14:39 All rights reserved.$
 */
@RestController
public class UserService implements UserFacade {

    @Value("${server.port}")
    private int serverPort;

    /**
     * say hello
     */
    @Override
    public String hello(String userName) {
        return serverPort + String.format(" hello, %s!", userName);
    }

    /**
     * say hello
     *
     * @param helloVO hello-info
     * @return hello return
     */
    @Override
    public String hello(UserHelloReq helloVO) {
        return String.format("hello, %s, %s", helloVO.getName(), helloVO.getMessage());
    }

    /**
     * query user info
     *
     * @param queryUserInfoReq query param
     * @return user info
     */
    @Override
    public QueryUserInfoResp queryUserInfo(QueryUserInfoReq queryUserInfoReq) {
        QueryUserInfoResp resp = new QueryUserInfoResp();
        resp.setName(queryUserInfoReq.getName());
        return resp;
    }
}
