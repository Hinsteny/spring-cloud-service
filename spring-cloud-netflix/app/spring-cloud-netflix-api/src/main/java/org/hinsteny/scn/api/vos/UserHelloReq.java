package org.hinsteny.scn.api.vos;

/**
 * @author Hinsteny
 * @version UserHelloVO: UserHelloVO 2019-07-12 09:27 All rights reserved.$
 */
public class UserHelloReq {

    private String name;

    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
