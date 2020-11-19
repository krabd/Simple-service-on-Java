package com.krab.rest.configs;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("public-api")
                .apiInfo(apiInfo())
                .select()
                .paths(apiPaths())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Test Rest Java API")
                .description("Test Rest Java API reference for developers")
                .termsOfServiceUrl("http://testrestjava.com")
                .license("krab License")
                .licenseUrl("krab@krab.com")
                .version("1.0")
                .build();
    }

    private Predicate<String> apiPaths() {
        return regex("/api.*");
    }
}

//http://localhost:8080/swagger-ui.html