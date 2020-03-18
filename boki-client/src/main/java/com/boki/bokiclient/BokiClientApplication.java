package com.boki.bokiclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.boki")
@EnableEurekaClient
public class BokiClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BokiClientApplication.class, args);
    }

}
