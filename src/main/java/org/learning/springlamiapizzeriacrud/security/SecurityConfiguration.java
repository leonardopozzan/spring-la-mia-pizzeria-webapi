package org.learning.springlamiapizzeriacrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    UserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        // creo un authentication provider basato su database (DAO)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // associo il mio DatabaseUserDetailsService
        provider.setUserDetailsService(userDetailsService());

        // associo il mio PasswordEncoder
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/ingredients", "/ingredients/**").hasAuthority("ADMIN")
                .requestMatchers("/offers/**").hasAuthority("ADMIN")
                .requestMatchers("/menu/create", "/menu/edit/**", "/menu/delete/**").hasAuthority("ADMIN")
                .requestMatchers("/", "/menu", "/menu/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/menu/**").hasAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling();
        http.csrf().disable();
        return http.build();
    }
}

