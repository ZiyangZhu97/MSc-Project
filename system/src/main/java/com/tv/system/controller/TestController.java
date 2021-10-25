package com.tv.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //返回json数据用RestController，返回页面用Controller

public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "success";
    }
}
