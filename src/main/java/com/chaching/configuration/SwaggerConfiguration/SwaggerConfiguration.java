package com.chaching.configuration.SwaggerConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


 @Configuration
 public class SwaggerConfiguration {

     // swagger URL to access the documentation at web  ->   http://localhost:9191/swagger-ui/index.html

     @Bean
     public OpenAPI customOpenAPI(SwaggerConfigProperties config) {

        License license = new License().name(config.getLicenseName()).url(config.getLicenseUrl());

        Info info = new Info().title(config.getTitle()).version(config.getApiVersion()).description(config.getDescription())
                            .termsOfService(config.getTermsOfService())
                            .license(license);
        
         return new OpenAPI()
                 .info(info);
     }
 }
