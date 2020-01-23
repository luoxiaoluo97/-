package com.boki.bokieureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BokiEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BokiEurekaApplication.class, args);
    }

}
