package com.spring.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mohamed Yosri",
                        email = "mhmd.you@yahoo.com"
                ),
                version = "vTest",
                title = "teacher_app api docs"
        )
)
public class OpenApiConfig {
}
