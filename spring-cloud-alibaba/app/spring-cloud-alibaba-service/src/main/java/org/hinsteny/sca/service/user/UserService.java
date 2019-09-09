package org.hinsteny.sca.service.user;

import org.apache.dubbo.config.annotation.Service;
import org.hinsteny.sca.api.facade.UserFacade;
import org.hinsteny.sca.api.vos.QueryUserInfoReq;
import org.hinsteny.sca.api.vos.QueryUserInfoResp;
import org.hinsteny.sca.api.vos.UserHelloReq;

/**
 * @author Hinsteny
 * @version UserService: 2019-09-05 14:25 All rights reserved.$
 */
@Service(version = "1.0.0")
public class UserService implements UserFacade {

    /**
     * say hello
     *
     * @param userName 用户名
     * @return hello message
     */
    @Override
    public String hello(String userName) {
        return userName;
    }

    /**
     * say hello
     *
     * @param helloVO hello-info
     * @return hello return
     */
    @Override
    public String hello(UserHelloReq helloVO) {
        return null == helloVO ? "" : helloVO.toString();
    }

    /**
     * query user info
     *
     * @param queryUserInfoReq query param
     * @return user info
     */
    @Override
    public QueryUserInfoResp queryUserInfo(QueryUserInfoReq queryUserInfoReq) {
        return null;
    }
}
