package org.pdsw.api_pdsw.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
public class SecurityConfig {

    // Centraliza a chave compartilhada para geração/validação do token
    @Bean
    public SecretKey jwtSecretKey() {
        return Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecretKey jwtSecretKey) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            // Habilita o CORS com a configuração definida abaixo
            .cors(Customizer.withDefaults())
            // Define a sessão como stateless pois utilizamos JWT
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Permite acesso aos endpoints do Swagger
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // Permite acesso aos endpoints do H2
                .requestMatchers("/h2-console/**").permitAll()
                // Permite login e criação de usuário sem token
                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                // Permite o frontend carregar os usuários sem token
                .requestMatchers(HttpMethod.GET, "/users").permitAll()
                // O restante exige token JWT válido
                .anyRequest().authenticated()
            )
            // Insere o filtro JWT antes do filtro padrão de autenticação
            .addFilterBefore(new JwtAuthenticationFilter(jwtSecretKey), UsernamePasswordAuthenticationFilter.class)
            // Desabilita o HTTP Basic
            .httpBasic(Customizer.withDefaults())
            // Desabilita os cabeçalhos para permitir frames (necessário para o H2 Console)
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite todas as origens. Em produção, especifique os domínios permitidos.
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica a configuração para todas as rotas
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}