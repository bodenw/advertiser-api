package com.boden.api.advertiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AdvertiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertiserApplication.class, args);
	}
	
	@Bean
	public Docket advertiserApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.boden.api.advertiser.controller"))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(createApiInfo());
	}
	
	private ApiInfo createApiInfo() {
		return new ApiInfoBuilder()
				.title("Advertiser API")
				.contact(new Contact("Wade Boden", "https://bodenw.github.io/", "wade.boden@gmail.com"))
				.description("API for advertisers")
				.build();
	}
}
