package com.basketball.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ManagementEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementEurekaApplication.class, args);
    }
}
