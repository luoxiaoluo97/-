package com.boki.bokieureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BokiEurekaApplication {

    public static void main(String[] args) {
        System.out.println("注册中心，启动！！！！");
        SpringApplication.run(BokiEurekaApplication.class, args);
    }

}
