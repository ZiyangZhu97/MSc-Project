package com.tv.server.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class MemberDto {

    /**
     * ID
     */
    private String id;

    /**
     * e-mail
     */
    private String email;

    /**
     * Password
     */
    private String password;

    /**
     * Name
     */
    private String name;

    /**
     * Registered Time
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registerTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", registerTime=").append(registerTime);
        sb.append("]");
        return sb.toString();
    }

}