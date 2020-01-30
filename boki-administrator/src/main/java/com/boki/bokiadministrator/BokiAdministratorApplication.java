package com.boki.bokiadministrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BokiAdministratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BokiAdministratorApplication.class, args);
    }

}
