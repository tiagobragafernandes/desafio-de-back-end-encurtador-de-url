package com.encurtator.link.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("URL Shortener API")
                        .description("API for URL shortening and statistics")
                        .version("v1.1.0")
                        .contact(new Contact()
                                .name("Tiago Fernandes")
                                .email("tibrafe@gmail.com")));
    }
}
