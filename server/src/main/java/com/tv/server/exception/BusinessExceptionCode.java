package com.tv.server.exception;

public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("Login name already existing"),
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
