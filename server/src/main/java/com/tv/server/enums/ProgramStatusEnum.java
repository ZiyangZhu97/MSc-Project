package com.tv.server.enums;

public enum ProgramStatusEnum {

    PUBLISH("P", "Publish"),
    DRAFT("D", "Draft");

    private String code;

    private String desc;

    ProgramStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
