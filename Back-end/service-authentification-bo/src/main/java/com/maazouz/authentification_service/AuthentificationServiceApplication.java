package com.maazouz.authentification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthentificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthentificationServiceApplication.class, args);
    }

}
