package com.boki.bokifeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.boki.bokiapi"})
@ComponentScan(basePackages = "com.boki")
public class BokiFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(BokiFeignApplication.class, args);
    }

}
