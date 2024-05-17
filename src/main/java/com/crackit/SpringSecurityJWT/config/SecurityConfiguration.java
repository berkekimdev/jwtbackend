package com.crackit.SpringSecurityJWT.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import static com.crackit.SpringSecurityJWT.user.Permission.*;
import static com.crackit.SpringSecurityJWT.user.Role.ADMIN;
import static com.crackit.SpringSecurityJWT.user.Role.MEMBER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors ->
                        cors.configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowCredentials(true);
                            config.addAllowedOrigin("http://localhost:5173"); // Geliştirme için
                            config.addAllowedHeader("*");
                            config.addAllowedMethod("*");
                            return config;
                        })
                )
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/crackit/v1/auth/*")
                                .permitAll()
                                .requestMatchers("/drugekle").hasAnyRole(ADMIN.name(), MEMBER.name())
                                .requestMatchers("/admin").hasRole(ADMIN.name())
                                .requestMatchers("/profile").hasAnyRole(ADMIN.name(), MEMBER.name())
                                .requestMatchers("/crackit/v1/management/**").hasAnyRole(ADMIN.name(), MEMBER.name())
                                .requestMatchers(GET, "/crackit/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MEMBER_READ.name())
                                .requestMatchers(POST, "/crackit/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MEMBER_CREATE.name())
                                .requestMatchers(GET, "/api/drugs").permitAll()  // GET isteklerine herkes erişebilir
                                .requestMatchers(GET, "/api/drugs/byGroup").permitAll()
                                .requestMatchers(POST, "/api/drugs").hasAnyRole("MEMBER", "ADMIN")  // POST isteklerine sadece MEMBER ve ADMIN erişebilir
                                .anyRequest().permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Güvenlik duvarını oluşturun ve sonucu doğrudan döndürün
    }
}
