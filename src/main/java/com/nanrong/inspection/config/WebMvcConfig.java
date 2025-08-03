package com.nanrong.inspection.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 排除API文档路径的静态资源处理
        registry.addResourceHandler("/v3/api-docs.xml")
                .addResourceLocations("classpath:/static/")
                .resourceChain(false);
    }
}