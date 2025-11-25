package com.example.prime_trade_assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http)throws Exception{
        http.csrf(
                csrf -> csrf.disable()).formLogin(
                        form -> form.disable()).httpBasic(
                                basic -> basic.disable()).authorizeHttpRequests(
                                        auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
