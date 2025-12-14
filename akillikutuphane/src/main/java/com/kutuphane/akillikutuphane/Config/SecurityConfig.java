package com.kutuphane.akillikutuphane.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF kapalı
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // API endpoints izinli
                .anyRequest().authenticated() // diğerleri güvenli
            );

        return http.build();
    }
}
