package com.home.nexmodemo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplicationConfiguration.class);

    @Bean
    public Docket api() {
        LOGGER.info("Preparing swagger");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.home.nexmodemo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Rest api for NexmoDemo",
                "SMS sending application.",
                "1.0",
                "Terms of service",
                new Contact("Ferenc Fekete", "", "ffekete84@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
