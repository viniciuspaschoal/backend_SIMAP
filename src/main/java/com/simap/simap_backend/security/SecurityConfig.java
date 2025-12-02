package com.simap.simap_backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Indica ao Spring que esta classe contém configurações
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter; // Nosso filtro personalizado

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // Desabilita CSRF porque nosso backend é stateless (não usa sessão)
                .csrf(csrf -> csrf.disable())

                // Define que NÃO usamos sessão → tudo via JWT
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Definição de rotas públicas e protegidas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll() // rota pública
                        .anyRequest().authenticated() // todo o resto precisa de token
                )

                // Adiciona nosso filtro ANTES do filtro padrão da Security
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                // Libera o httpBasic só para evitar erros (não usamos)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // AuthManager padrão do Spring (obrigatório mesmo sem usar login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
