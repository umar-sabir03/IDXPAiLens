package com.pilog.mdm.cloud.idxpailens.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {
		
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
						
	}

	private ApiInfo getInfo() {
		return new ApiInfo ("AiLens Application",
				"This Project Is developed by Pilog Developers","1.0","Terms of Service",
				new springfox.documentation.service.Contact("pilog","https://************/","**********@gmail.com"),
				"Licence of API","Licence of URl",Collections.emptyList());
	}
}
