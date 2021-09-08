package task.orange.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;*/

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args); 
	}


	@Bean
	public Docket swaggerConfig(){

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("task.orange.assignment"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){

		return new ApiInfo(
			"Assignment API",
				"The API Help to model and manipulate the three entities (Supplier, Product, and Order) and the relationship between each other",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Muhammad Mustafa", "https://github.com/mumusta", "mumusta3.14@gmail.com"),
				"API License",
				"https://github.com/mumusta",
				Collections.emptyList()

		);
	}
}
