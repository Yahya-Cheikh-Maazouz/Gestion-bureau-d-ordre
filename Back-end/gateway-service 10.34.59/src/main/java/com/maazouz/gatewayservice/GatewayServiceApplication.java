package com.maazouz.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }




    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/v1/us/**").uri("lb://AUTHENTICATION-SERVICE"))
                .route(p -> p.path("/api/v1/login/**").uri("lb://AUTHENTICATION-SERVICE"))
                .route(p -> p.path("/api/v1/registration/**").uri("lb://AUTHENTICATION-SERVICE"))
                .route(p -> p.path("/api/v1/bo/**").uri("lb://GESTION-BO-SERVICE"))
                .build();
    }





}
