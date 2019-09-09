package org.hinsteny.sca.api.facade;

import org.hinsteny.sca.api.vos.QueryUserInfoReq;
import org.hinsteny.sca.api.vos.QueryUserInfoResp;
import org.hinsteny.sca.api.vos.UserHelloReq;

/**
 * 用户操作门面.
 *
 * @author Hinsteny
 * @version UserFacade: 2019-09-04 18:45 All rights reserved.$
 */
public interface UserFacade {

    /**
     * say hello
     *
     * @param userName 用户名
     * @return hello message
     */
    String hello(String userName);

    /**
     * say hello
     *
     * @param helloVO hello-info
     * @return hello return
     */
    String hello(UserHelloReq helloVO);

    /**
     * query user info
     *
     * @param queryUserInfoReq query param
     * @return user info
     */
    QueryUserInfoResp queryUserInfo(QueryUserInfoReq queryUserInfoReq);

}
