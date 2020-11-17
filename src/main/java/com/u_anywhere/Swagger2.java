package com.u_anywhere;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author zs
 * @version 1.0 
 * @date 2019年3月18日
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.u_anywhere"))
				.paths(PathSelectors.any())
				.build();
	}
	
	@SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("swagger-bootstrap-ui RESTful APIs")
	            .description("swagger-bootstrap-ui")
	            .termsOfServiceUrl("http://localhost:8113/")
	            .contact("developer@mail.com")
	            .version("1.0")
	            .build();
	}
}