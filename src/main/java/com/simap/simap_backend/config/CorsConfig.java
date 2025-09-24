package com.simap.simap_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitindo todas as origens
        registry.addMapping("/**")  // Libera todas as rotas da API
                .allowedOrigins("http://localhost:5173")  // URL do front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permite qualquer cabeçalho
                .allowCredentials(true);  // Se precisar enviar cookies ou credenciais
    }
}