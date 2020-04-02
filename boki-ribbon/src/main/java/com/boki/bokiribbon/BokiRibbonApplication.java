package com.boki.bokiribbon;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.boki.bokiribbon"})
@ComponentScan(basePackages = "com.boki")
public class BokiRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(BokiRibbonApplication.class, args);
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if(null != attributes){
                HttpServletRequest request = attributes.getRequest();
                if (request.getSession().getAttribute("UID") != null
                        && request.getSession().getAttribute("mail") != null
                        && request.getSession().getAttribute("mail") != null
                        && request.getSession().getAttribute("roleId") != null) {
                    requestTemplate.header("UID", request.getSession().getAttribute("UID").toString());
                    String userName;
                    try {
                        userName = URLEncoder.encode(request.getSession().getAttribute("userName").toString(),"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        log.warn("userName编码错误，原因是："+e.getMessage());
                        throw new RuntimeException();
                    }
                    requestTemplate.header("userName", userName);
                    requestTemplate.header("mail", request.getSession().getAttribute("mail").toString());
                    requestTemplate.header("roleId", request.getSession().getAttribute("roleId").toString());
                }
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        if(name.equals("cookie")){
                            String values = request.getHeader(name);
                            requestTemplate.header(name, values);
                        }
                    }
                }
            }
        };
    }
}
