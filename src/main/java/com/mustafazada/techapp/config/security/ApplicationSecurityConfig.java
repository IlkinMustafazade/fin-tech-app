package com.mustafazada.techapp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ApplicationSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) {
        try {
            return httpSecurity
                    .getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder)
                    .and().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        try {
            return httpSecurity
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/api/v1/registration", "/api/v1/login")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and().headers()
                    .and().formLogin().disable().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
