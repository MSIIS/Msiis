package com.util.config;

/**
 * Created by lenovo on 2015/7/11.
 */
public enum UserConfig {

    USER_LOGON_SESSION("LOGIN_USER","已经登陆的用户信息","存储与session中");

    private  String code ;
    private String name ;
    private String desc ;

    UserConfig(String code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
