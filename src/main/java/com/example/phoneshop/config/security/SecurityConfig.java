package com.example.phoneshop.config.security;

import com.example.phoneshop.config.jwt.JwtLoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    private final PasswordConfig passwordConfig;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .addFilter(new JwtLoginFilter(authenticationManager))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                );

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.builder()
                .username("dara")
                .password(passwordEncoder.encode("dara123"))
                .authorities(RoleEnum.SALE.getAuthorities())
                .build();
        UserDetails user2 = User.builder()
                .username("thida")
                .password(passwordEncoder.encode("thida123"))
                .authorities(RoleEnum.ADMIN.getAuthorities())
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
