package com.chaching.configuration.SwaggerConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;


 @Configuration
 public class SwaggerConfiguration {

    final String securitySchemename = "bearerAuth";
    final String sourceHeader = "Source";

     // swagger URL to access the documentation at web  ->   http://localhost:9191/swagger-ui/index.html

     @Bean
     public OpenAPI customOpenAPI(SwaggerConfigProperties config) {

        License license = new License().name(config.getLicenseName()).url(config.getLicenseUrl());

        Info info = new Info().title(config.getTitle()).version(config.getApiVersion()).description(config.getDescription())
                            .termsOfService(config.getTermsOfService())
                            .license(license);

        Components components = new Components();
        components.addResponses("forbidden", createErrorApiResponse(HttpStatus.FORBIDDEN)).addResponses("unauthorized", createErrorApiResponse(HttpStatus.UNAUTHORIZED));
        components.addSecuritySchemes(securitySchemename, new SecurityScheme().name(securitySchemename).scheme("bearer").bearerFormat("JWT").type(Type.HTTP));
        components.addSecuritySchemes(sourceHeader, new SecurityScheme().type(Type.APIKEY).in(SecurityScheme.In.HEADER).name(sourceHeader));

        Map<String, SecurityScheme> securityMap = new HashMap<>();
        securityMap.put(securitySchemename, new SecurityScheme().name(securitySchemename).scheme("bearer").bearerFormat("JWT").type(Type.HTTP));
        securityMap.put(sourceHeader, new SecurityScheme().name(sourceHeader).scheme("Source").type(Type.APIKEY).in(SecurityScheme.In.HEADER));

        components.securitySchemes(securityMap);

        List<SecurityRequirement> securityRequirements = new ArrayList<>();
        securityRequirements.add(new SecurityRequirement().addList(securitySchemename));
        securityRequirements.add(new SecurityRequirement().addList(sourceHeader));
        
         return new OpenAPI()
                 .info(info)
                 .components(components)
                 .security(securityRequirements);
     }

     private ApiResponse createErrorApiResponse(HttpStatus status){
        return new ApiResponse().description(status.getReasonPhrase());
     }
 }
