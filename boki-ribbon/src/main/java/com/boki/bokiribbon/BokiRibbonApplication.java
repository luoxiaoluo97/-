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

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.boki.bokiribbon"})
@ComponentScan(basePackages = "com.boki")
public class BokiRibbonApplication {

    public static final String TOKEN = "TOKEN";

    public static void main(String[] args) {
        SpringApplication.run(BokiRibbonApplication.class, args);
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if(null != attributes){
                HttpServletRequest request = attributes.getRequest();
                if (request.getHeader(TOKEN) != null) {
                    requestTemplate.header(TOKEN, request.getHeader(TOKEN));
                }
            }
        };
    }
}
