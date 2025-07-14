package edu.br.ifpr.controle_de_locadora_vhs.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Libera todas as rotas
            )
            .csrf(csrf -> csrf.disable()) // Desativa proteção CSRF para facilitar post com formulário simples
            .formLogin(form -> form.disable()) // Desativa a tela de login automática
            .httpBasic(httpBasic -> httpBasic.disable()); // Desativa autenticação básica

        return http.build();
    }
}