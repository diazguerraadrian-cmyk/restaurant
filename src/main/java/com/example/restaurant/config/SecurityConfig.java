package com.example.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/hola", "/adios", "/login",
                                "/register", "/css/**", "/images/**", "/webjars/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/restaurates").permitAll()
                        .requestMatchers(HttpMethod.GET, "/restaurates/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/restaurates").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurates/desactivar/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurates/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurates/edit/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/platos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/plato/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/platos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/platos/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/platos/edit/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/reviews").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reviews").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/reviews/new").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/reviews/edit/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/reviews/delete/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/pedidos").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/pedidos/new").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/plato/**").hasRole("USER")

                        .anyRequest().authenticated()


        );
        http.formLogin(form ->
                form.loginPage("/login")
                        .defaultSuccessUrl("/restaurantes", true)
                        .permitAll()
        );

        return http.build();
    }
}
