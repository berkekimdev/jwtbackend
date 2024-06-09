package com.crackit.SpringSecurityJWT.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

// Bu sınıf, Spring Security yapılandırmasını sağlar.
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    // Kimlik doğrulama sağlayıcısı bileşeni
    private final AuthenticationProvider authenticationProvider;
    // JWT kimlik doğrulama filtresi bileşeni
    private final JwtAuthFilter jwtAuthFilter;

    // Güvenlik filtre zincirini tanımlayan bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CORS konfigürasyonu
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // CSRF korumasını devre dışı bırakma
                .csrf(AbstractHttpConfigurer::disable)
                // HTTP isteklerini yetkilendirme
                .authorizeHttpRequests(auth -> auth
                        // /crackit/v1/auth/** yolu herkese açık
                        .requestMatchers("/crackit/v1/auth/**").permitAll()
                        // /drugekle yolu sadece ADMIN ve MEMBER rolüne sahip kullanıcılar için erişilebilir
                        .requestMatchers("/drugekle").hasAnyRole("ADMIN", "MEMBER")
                        // /admin yolu sadece ADMIN rolüne sahip kullanıcılar için erişilebilir
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // /profile yolu sadece ADMIN ve MEMBER rolüne sahip kullanıcılar için erişilebilir
                        .requestMatchers("/profile").hasAnyRole("ADMIN", "MEMBER")
                        // /crackit/v1/management/** GET istekleri sadece ADMIN_READ yetkisine sahip kullanıcılar için erişilebilir
                        .requestMatchers(GET, "/crackit/v1/management/**").hasAuthority("ADMIN_READ")
                        // /crackit/v1/management/** POST istekleri sadece ADMIN_CREATE yetkisine sahip kullanıcılar için erişilebilir
                        .requestMatchers(POST, "/crackit/v1/management/**").hasAuthority("ADMIN_CREATE")
                        // /api/drugs/** GET istekleri herkese açık
                        .requestMatchers(GET, "/api/drugs/**").permitAll()
                        // /api/drugs POST istekleri sadece MEMBER rolüne sahip kullanıcılar için erişilebilir
                        .requestMatchers(POST, "/api/drugs").hasRole("MEMBER")
                        // /api/drugs/** PUT istekleri sadece ADMIN rolüne sahip kullanıcılar için erişilebilir
                        .requestMatchers(PUT, "/api/drugs/**").hasRole("ADMIN")
                        // /api/drugs/** DELETE istekleri sadece ADMIN rolüne sahip kullanıcılar için erişilebilir
                        .requestMatchers(DELETE, "/api/drugs/**").hasRole("ADMIN")
                        // Diğer tüm istekler kimlik doğrulaması gerektirir
                        .anyRequest().authenticated())
                // Oturum yönetimini belirleme
                .sessionManagement(sm -> sm.sessionCreationPolicy(STATELESS))
                // Kimlik doğrulama sağlayıcısını belirleme
                .authenticationProvider(authenticationProvider)
                // JWT kimlik doğrulama filtresini ekleme
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Güvenlik zincirini oluştur ve döndür
        return http.build();
    }

    // CORS konfigürasyon kaynağını tanımlayan bean
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Kimlik bilgilerine izin ver
        config.addAllowedOriginPattern("*"); // Bu şekilde tüm originlere izin verilir
        config.addAllowedHeader("*"); // Tüm başlıklara izin ver
        config.addAllowedMethod("*"); // Tüm HTTP metodlarına izin ver
        source.registerCorsConfiguration("/**", config); // Bu konfigürasyonu tüm yollar için uygula
        return source;
    }
}
