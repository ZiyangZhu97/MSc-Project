package com.tv.server.enums;

public enum ProgramTypeEnum {

    MOVIE("M", "Movie"),
    SERIES("S", "Series");


    private String code;

    private String desc;

    ProgramTypeEnum(String code, String desc) {
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
