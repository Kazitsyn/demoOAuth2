package com.example.testOAuth2Res.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
            authorizationManagerRequestMatcherRegistry
                    .anyRequest()
                    .authenticated())
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
            httpSecurityOAuth2ResourceServerConfigurer
                    .jwt(Customizer
                            .withDefaults()));
        return httpSecurity.build();
    }
}
