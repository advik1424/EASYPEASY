package com.easypeasy.EPS_BACKEND.Config;

import com.easypeasy.EPS_BACKEND.AUTH.filter.JwtAuthenticationFilter;
import com.easypeasy.EPS_BACKEND.AUTH.filter.OAuth2RoleCaptureFilter;
import com.easypeasy.EPS_BACKEND.AUTH.handler.OAuth2AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2RoleCaptureFilter oAuth2RoleCaptureFilter;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            OAuth2RoleCaptureFilter oAuth2RoleCaptureFilter,
            OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.oAuth2RoleCaptureFilter = oAuth2RoleCaptureFilter;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .addFilterBefore(
                        oAuth2RoleCaptureFilter,
                        OAuth2AuthorizationRequestRedirectFilter.class
                )

                .authorizeHttpRequests(auth -> auth

                        // Public
                        .requestMatchers(
                                "/users/**",
                                "/auth/login",
                                "/oauth2/**",
                                "/login/**"
                        ).permitAll()

                        // Student APIs
                        .requestMatchers(
                                "/order",
                                "/order/my-orders",
                                "/payment"
                        ).hasRole("STUDENT")

                        // Shopkeeper APIs
                        .requestMatchers(
                                "/order/shop",
                                "/order/*/confirm",
                                "/shop/**"
                        ).hasRole("SHOPKEEPER")

                        // Other role APIs
                        .requestMatchers("/student")
                        .hasRole("STUDENT")

                        .requestMatchers("/shop")
                        .hasRole("SHOPKEEPER")

                        .requestMatchers("/admin")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                // Google OAuth2
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}