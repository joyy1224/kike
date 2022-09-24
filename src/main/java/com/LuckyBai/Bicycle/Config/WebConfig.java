package com.LuckyBai.Bicycle.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Slf4j
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    /**
     * 设置访问静态资源的映射
     * @param
     */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        log.info("静态资源映射。。。。");
//        registry.addResourceHandler("/backend/**")
//                .addResourceLocations("classpath:/backend/");
//        registry.addResourceHandler("/front/**")
//                .addResourceLocations("classpath:/front/");
//    }
}
