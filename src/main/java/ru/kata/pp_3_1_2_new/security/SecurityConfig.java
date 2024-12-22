package ru.kata.pp_3_1_2_new.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginSuccessHandler loginSuccessHandler) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/*", "/login").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/logout").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .successHandler(loginSuccessHandler)
                        .permitAll()
                );

        return http.build();
    }
}