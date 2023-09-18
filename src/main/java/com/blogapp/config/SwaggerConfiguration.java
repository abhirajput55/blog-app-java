package com.blogapp.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.blogapp.constants.ApiConstants.AUTHORIZATION;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	
	
	private ApiKey apiKeys() {
		return new ApiKey("JWT", AUTHORIZATION, "header");
	}
	
	private List<SecurityContext> securityContexts() {
		return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
	}
	
	private List<SecurityReference> securityReferences(){
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {authorizationScope}));
	}
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select()
//				Access to all api's
				.apis(RequestHandlerSelectors.any())
//				Access to all paths
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo( 
//				Application Title
				"Blogging Application API's", 
//				Description about application
				"This is a backend project api's of blogging application using Java 11 and Spring Boot 2.", 
//				Application Version
				"1.0", 
				"Terms of service.", 
//				Contact info Name, Website URL and Mail Id
				new Contact("Abhishek Ingle", "http://abhishek.com", "abhishekingle2000@gmail.com"), 
				"License of Api", 
				"Api License URL", 
				Collections.emptyList());
	}

}
