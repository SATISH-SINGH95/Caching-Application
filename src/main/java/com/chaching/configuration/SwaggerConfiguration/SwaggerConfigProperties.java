package com.chaching.configuration.SwaggerConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class SwaggerConfigProperties {

    @Value("${api.version}")
    private String apiVersion;

    @Value("${application-title}")
    private String title;

    @Value("${application-description}")
    private String description;

    @Value("${application-terms-of-service}")
    private String termsOfService;

    @Value("${application-license-name}")
    private String licenseName;

    @Value("${application-license-url}")
    private String licenseUrl;

//    private String externalDocTitle;
//
//    private String externalDocUrl;

// comment
}
