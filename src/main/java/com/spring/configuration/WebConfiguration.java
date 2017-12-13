package com.spring.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * User: GaoYuan
 * Date: 17/12/13
 * Time: 13:50
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    /**
     * 设置前端请求跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowCredentials(false).maxAge(3600);
    }
}
