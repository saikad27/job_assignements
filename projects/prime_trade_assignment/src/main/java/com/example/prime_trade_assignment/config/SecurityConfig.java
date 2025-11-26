package com.example.prime_trade_assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration
public class SecurityConfig {

/*    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http)throws Exception{
        http.csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        /*
        Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer = new Customizer<CsrfConfigurer<HttpSecurity>>(){
          public void customize(CsrfConfigurer<HttpSecurity> customizer){
            customizer.disable();
          }
        };
        http.csrf(csrfCustomizer);
         */

        //The following lambda expression does above
        http.csrf(customizer -> customizer.disable());//disabling the need of csrf tokens for post requests

        //http.authorizeHttpRequests(request -> request.anyRequest().authenticated());//Use spring security's user authentication feature
        //http.formLogin(Customizer.withDefaults());//display a from for user authentication

        http.authorizeHttpRequests(request -> request.anyRequest().permitAll());
        http.formLogin(form -> form.disable());
        http.httpBasic(Customizer.withDefaults());//Allow postman to receive rest responses

        return http.build();    //returns a SecurityFilterChain instance
    }
}
