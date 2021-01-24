package com.channelpilot.productconnect.config;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String DESCRIPTION = "Documentation of the channelpilot REST API to connect a channelpilot to external aplications. \r\n"
				+ "\r\n"
				+ "Authorization & security\r\n"
				+ "\r\n"
				+ "authentication API endpoint URL is : /api/user\r\n"
				+ "\r\n"
				+ "every request has to be sent over HTTPS and must :\r\n"
				+ "\r\n"
				+ "Contain a valid API JWT Token get from /api/user endpoint . You'll have to provide your API token via Authorization-Header: Authorization: Bearer yourToken.Every call to API is secured by an API token\r\n"
				+ "\r\n"
				+ "Get API token\r\n"
				+ "\r\n"
				+ "To enabe access to the REST API, a user has to be get token by username and password from /api/user API Afterwards, a randomly generated API access key will be provided, which needs to be included in your API requests for authentication.";

	public static final Contact DEFAULT_CONTACT = new Contact("Nazila Golchin", "https://github.com/nazilagolchin",
			"nazila.golchin@gmail.com");

	public static final ApiInfo V1_API_INFO = new ApiInfo("channelpilot API 1.0",
			DESCRIPTION
			+ "",
			"1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	public static final ApiInfo V2_API_INFO = new ApiInfo("channelpilot API 2.0",
			DESCRIPTION, "2.0",
			"urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");

	/**
	 * @return Docket for version 1.0 builder for version 1.0
	 */
	@Bean
	public Docket swaggerProductApi10() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("demorestapi-1.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.channelpilot.productconnect.controller"))
				.paths(PathSelectors.ant("/api/v1/**")).build().produces(Sets.newHashSet(APPLICATION_JSON_VALUE))
				.consumes(Sets.newHashSet(APPLICATION_JSON_VALUE)).apiInfo(V1_API_INFO);
	}

	/**
	 * @return Docket for version 2.0 builder for version 2.0
	 */
	@Bean
	public Docket swaggerProductApi20() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("demorestapi-2.0").select()
				.apis(RequestHandlerSelectors.basePackage("com.channelpilot.productconnect.controller"))
				.paths(PathSelectors.ant("/api/v2/**")).build().produces(Sets.newHashSet(APPLICATION_JSON_VALUE))
				.consumes(Sets.newHashSet(APPLICATION_JSON_VALUE)).apiInfo(V2_API_INFO);
	}
}
