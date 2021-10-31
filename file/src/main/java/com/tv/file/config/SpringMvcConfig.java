package com.tv.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/f/**").addResourceLocations("file:D:/MScProject/tv/file/local_file/program");
    }
//访问文件的全地址http://127.0.0.1:9003/file/f/program/cover/xxx.jpg
}