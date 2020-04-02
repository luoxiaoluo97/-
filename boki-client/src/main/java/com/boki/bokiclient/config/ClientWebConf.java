package com.boki.bokiclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: LJF
 * @Date: 2020/3/20
 * @Description:
 */
@Configuration
public class ClientWebConf implements WebMvcConfigurer {

    @Autowired
    private ClientInterceptor clientInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(clientInterceptor);
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/p/**",                            //开放性页面
                "/",                                //首页
                "/login/register",                   //注册
                "/login/sendCheckCode/*",           //发送邮箱校验码
                "/login",                               //登录
                "/static/**"                         //静态资源
        );
    }

}
