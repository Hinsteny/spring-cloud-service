package org.hinsteny.sca.api.vos;

/**
 * @author Hinsteny
 * @version UserHelloReq: 2019-09-04 18:46 All rights reserved.$
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

    @Override
    public String toString() {
        return "UserHelloReq{" +
            "name='" + name + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
