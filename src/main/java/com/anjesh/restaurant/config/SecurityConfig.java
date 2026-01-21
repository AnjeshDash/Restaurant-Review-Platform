package com.anjesh.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth->
                auth.anyRequest().authenticated()
        )
                .oauth2ResourceServer(oauth2->
                        oauth2.jwt(jwt->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                        ))
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrf->csrf.disable());
        return http.build();
    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        return new JwtAuthenticationConverter();
    }
}
