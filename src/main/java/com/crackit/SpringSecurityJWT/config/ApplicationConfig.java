package com.crackit.SpringSecurityJWT.config;

import com.crackit.SpringSecurityJWT.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration, bu sınıfın bir konfigürasyon sınıfı olduğunu belirtir ve
// Spring konteynerına bu sınıfın bean tanımlamaları içerdiğini bildirir.
@Configuration

// @RequiredArgsConstructor, Lombok tarafından sağlanır ve sınıftaki tüm final alanlar için
// bir yapıcı (constructor) oluşturur. Bu, bağımlılık enjeksiyonunu kolaylaştırır.
@RequiredArgsConstructor
public class ApplicationConfig {

    // UserRepository, kullanıcı veritabanı işlemleri için kullanılır.
    private final UserRepository userRepository;

    // PasswordEncoder bean tanımı. Şifreleri şifrelemek ve doğrulamak için kullanılır.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder, BCrypt algoritmasını kullanarak şifreleri şifreler.
        return new BCryptPasswordEncoder();
    }

    // UserDetailsService bean tanımı. Kullanıcı bilgilerini yüklemek için kullanılır.
    @Bean
    public UserDetailsService userDetailsService() {
        // Lambda fonksiyonu ile kullanıcıyı e-posta adresine göre bulur.
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // AuthenticationProvider bean tanımı. Kimlik doğrulama işlemlerini sağlar.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // DaoAuthenticationProvider, kullanıcı doğrulama işlemleri için kullanılır.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // Kullanıcı detaylarını yüklemek için userDetailsService kullanılır.
        authProvider.setUserDetailsService(userDetailsService());
        // Şifreleri doğrulamak için passwordEncoder kullanılır.
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // AuthenticationManager bean tanımı. Kimlik doğrulama yöneticisini sağlar.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // AuthenticationConfiguration nesnesinden kimlik doğrulama yöneticisini alır.
        return config.getAuthenticationManager();
    }

}
