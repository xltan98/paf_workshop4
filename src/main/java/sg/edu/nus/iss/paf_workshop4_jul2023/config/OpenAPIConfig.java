package sg.edu.nus.iss.paf_workshop4_jul2023.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI(){

        return new OpenAPI().info(new Info()
        .title("my paf day 24 workshop")
        .description("day 24 workshop 4")
        .version("version 1.0"));

    }
    
}