package com.ruandevjs.spring_boot.blog_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain secutiryFilterChain(HttpSecurity httpSecurity) {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests(http -> {
            // PROBLEMA: liberar "/posts/**" torna públicas também as operações de criação,
            // atualização e exclusão. Isso permite alterações sem autenticação e agrava a falta
            // de verificação de propriedade nos casos de uso. Libere apenas leituras públicas e
            // exija autenticação nas rotas POST, PUT e DELETE.
            http.requestMatchers("/users/**", "/posts/**", "/auth").permitAll();
            http.anyRequest().authenticated();
        });
        return httpSecurity.build();
    }

}
