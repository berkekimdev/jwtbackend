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
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

// @Configuration, bu sınıfın bir konfigürasyon sınıfı olduğunu belirtir ve Spring konteynerına
// bu sınıfın bean tanımlamaları içerdiğini bildirir.
@Configuration

// @EnableWebSecurity, bu sınıfın web güvenlik yapılandırması sağladığını belirtir.
@EnableWebSecurity

// @RequiredArgsConstructor, Lombok tarafından sağlanır ve sınıftaki tüm final alanlar için
// bir yapıcı (constructor) oluşturur. Bu, bağımlılık enjeksiyonunu kolaylaştırır.
@RequiredArgsConstructor

// @EnableMethodSecurity, metod bazında güvenlik sağlanmasını etkinleştirir.
@EnableMethodSecurity
public class SecurityConfiguration {

    // AuthenticationProvider, kimlik doğrulama işlemlerini sağlayan bileşendir.
    private final AuthenticationProvider authenticationProvider;

    // JwtAuthFilter, JWT kimlik doğrulama filtreleme işlemlerini sağlayan bileşendir.
    private final JwtAuthFilter jwtAuthFilter;

    // Güvenlik filtre zincirini tanımlayan bean metodu
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CORS konfigürasyonu
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    config.addAllowedOrigin("http://localhost:5173"); // Geliştirme için
                    config.addAllowedHeader("*");
                    config.addAllowedMethod("*");
                    return config;
                }))
                // CSRF korumasını devre dışı bırakma
                .csrf(AbstractHttpConfigurer::disable)
                // HTTP isteklerini yetkilendirme
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/crackit/v1/auth/*").permitAll() // Bu yollara erişim izni ver
                        .requestMatchers("/drugekle").hasAnyRole(ADMIN.name(), MEMBER.name()) // Bu yola sadece ADMIN veya MEMBER rolüne sahip kullanıcılar erişebilir
                        .requestMatchers("/admin").hasRole(ADMIN.name()) // Bu yola sadece ADMIN rolüne sahip kullanıcılar erişebilir
                        .requestMatchers("/profile").hasAnyRole(ADMIN.name(), MEMBER.name()) // Bu yola sadece ADMIN veya MEMBER rolüne sahip kullanıcılar erişebilir
                        .requestMatchers("/crackit/v1/management/**").hasAnyRole(ADMIN.name(), MEMBER.name()) // Bu yola sadece ADMIN veya MEMBER rolüne sahip kullanıcılar erişebilir
                        .requestMatchers(GET, "/crackit/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MEMBER_READ.name()) // Bu GET isteklerine sadece ADMIN_READ veya MEMBER_READ yetkisine sahip kullanıcılar erişebilir
                        .requestMatchers(POST, "/crackit/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MEMBER_CREATE.name()) // Bu POST isteklerine sadece ADMIN_CREATE veya MEMBER_CREATE yetkisine sahip kullanıcılar erişebilir
                        .requestMatchers(GET, "/api/drugs").permitAll() // Bu GET isteklerine herkes erişebilir
                        .requestMatchers(GET, "/api/drugs/byGroup").permitAll() // Bu GET isteklerine herkes erişebilir
                        .requestMatchers(POST, "/api/drugs").hasAnyRole("MEMBER", "ADMIN") // Bu POST isteklerine sadece MEMBER veya ADMIN rolüne sahip kullanıcılar erişebilir
                        .requestMatchers(PUT, "/api/drugs/**").hasRole(ADMIN.name()) // Bu PUT isteklerine sadece ADMIN rolüne sahip kullanıcılar erişebilir
                        .requestMatchers(DELETE, "/api/drugs/**").hasRole(ADMIN.name()) // Bu DELETE isteklerine sadece ADMIN rolüne sahip kullanıcılar erişebilir
                        .anyRequest().permitAll()) // Diğer tüm isteklere erişim izni ver
                // Oturum yönetimini belirleme
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                // Kimlik doğrulama sağlayıcısını belirleme
                .authenticationProvider(authenticationProvider)
                // JWT kimlik doğrulama filtresini ekleme
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Güvenlik duvarını oluşturun ve sonucu doğrudan döndürün
        return http.build();
    }
}
