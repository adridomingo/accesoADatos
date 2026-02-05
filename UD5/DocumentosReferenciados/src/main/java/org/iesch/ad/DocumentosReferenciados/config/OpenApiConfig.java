package org.iesch.ad.DocumentosReferenciados.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpeApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de prueba de mongoDB")
                        .version("1.0.0")
                        .description("Pruebas realizadas en clase"));
    }

}
