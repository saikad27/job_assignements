package com.example.prime_trade_assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;


@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(customizer -> customizer.disable());//disabling the need of csrf tokens for post requests
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/login", "/signup", "/css/**","/js/**","/dashboard","/dashboard.html", "/signup.html", "/images/**", "/favicon.ico", "/login.html").permitAll().anyRequest().authenticated());
        http.formLogin(form -> form.disable());
        http.exceptionHandling(ex->ex.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));
        http.httpBasic(Customizer.withDefaults());//Allow postman to receive rest responses


        return http.build();    //returns a SecurityFilterChain instance
    }
}
