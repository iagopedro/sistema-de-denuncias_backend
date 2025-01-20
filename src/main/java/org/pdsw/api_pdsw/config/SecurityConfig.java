package org.pdsw.api_pdsw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for testing purposes (not recommended in production)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login").permitAll() // Allow unauthenticated access to POST /user
                .requestMatchers("/user").permitAll()
                .anyRequest().authenticated() // Authenticate other requests
            )
            .httpBasic(httpBasic -> {});
        return http.build();
    }
}