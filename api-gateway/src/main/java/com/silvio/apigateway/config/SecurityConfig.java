package com.silvio.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {

        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable) //il method reference sostituisce csrf -> csrf.disable()
                .authorizeExchange(excange -> excange.pathMatchers("/eureka/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated())
                .oauth2ResourceServer(oath2 -> oath2.jwt(Customizer.withDefaults()));

        return serverHttpSecurity.build();
    }
}
