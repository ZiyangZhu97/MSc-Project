package com.tv.server.exception;

public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("Login name already exist"),
    LOGIN_ERROR("Wrong username or password"),
    LOGIN_MEMBER_ERROR("Wrong email or password"),

    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
