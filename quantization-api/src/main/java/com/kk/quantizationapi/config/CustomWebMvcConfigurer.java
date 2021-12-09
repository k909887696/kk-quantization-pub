package com.kk.quantizationapi.config;

import com.kk.quantizationapi.intercepter.ParameterIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: kk
 * @Date: 2021/11/18 17:54
 * 拦截器 、监听器注册
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册多个Interceptor  注意路径的写法
        registry.addInterceptor(new ParameterIntercepter()).addPathPatterns("/api/**");
       // registry.addInterceptor(new TwoIntercepter()).addPathPatterns("/api2/*/**");
        //注册某个拦截器的时候，同时排除某些不拦截的路径
        //registry.addInterceptor(new TwoIntercepter()).addPathPatterns("/api2/*/**").excludePathPatterns("/api2/xxx/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }



}
