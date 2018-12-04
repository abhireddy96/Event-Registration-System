package com.abhi.reddy.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Configure api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket configureApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.abhi.reddy.controller"))
				.paths(PathSelectors.any())  
				.build()
				.apiInfo(getApiInfo());
	}

	/**
	 * Gets the api info.
	 *
	 * @return the api info
	 */
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Event Registration System", 
				"Event management admin register an employee with an event, so that employees can get notifications regarding their favorite events.", 
				"1.0", 
				"Terms of service", 
				new Contact("Abhishek Reddy", "M1041919", "Abhishek.Reddy@mindtree.com"), 
				"Mindtree Ltd.", "www.mindtree.com");
	}

}
