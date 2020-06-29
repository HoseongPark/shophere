package com.shophere.book.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfig {

    private String version;
    private String title;
    private final TypeResolver typeResolver;

    @Bean
    public Docket apiV1() {
        version = "V1";
        title = "ShopHere API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class), typeResolver.resolve(Page.class))
                )
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shophere.book.api"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(apiInfo(title,version));

    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger Create Api Docs",
                version,
                "www.example.com",
                new Contact("Contact Me", "www.example.com", "ghtjd5689@gmail.com"),
                "Licenses",
                "www.example.com",
                new ArrayList<>());
    }

    @Getter @Setter
    @ApiModel
    static class Page {

        @ApiModelProperty(value = "페이지 [0...N]")
        private Integer page;

        @ApiModelProperty(value = "페이지 사이즈")
        private Integer size;
    }
}