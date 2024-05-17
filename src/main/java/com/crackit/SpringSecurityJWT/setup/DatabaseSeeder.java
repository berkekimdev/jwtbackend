package com.crackit.SpringSecurityJWT.setup;

import com.crackit.SpringSecurityJWT.user.Role;
import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Admin kullanıcısını kontrol et ve yoksa ekle
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User admin = User.builder()
                    .eczaneAdi("Admin Eczane")
                    .city("İstanbul")
                    .email("admin@example.com")
                    .phoneNumber("05532106873")
                    .password(passwordEncoder.encode("adminpassword")) // Şifre hashleniyor
                    .address("Admin Address")
                    .district("Admin District")
                    .latitude(41.0082)
                    .longitude(28.9784)
                    .role(Role.valueOf("ADMIN")) // JSON string değerini enum'a çevirme
                    .isActive(true) // Admin başlangıçta aktif
                    .build();

            userRepository.save(admin);
        }
    }
}
