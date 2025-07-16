package com.app.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedHeaders(List.of("Authorization","Origin","Accept","Content-Type"));
        configuration.setExposedHeaders(List.of("Authorization","Set-Cookie"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));

        source.registerCorsConfiguration("/**",configuration);
        return new CorsFilter(source);
    }
}
