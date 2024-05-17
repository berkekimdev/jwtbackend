package com.crackit.SpringSecurityJWT.setup;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.entities.MemberDrugStock;
import com.crackit.SpringSecurityJWT.repositories.DrugRepository;
import com.crackit.SpringSecurityJWT.repositories.MemberDrugStockRepository;
import com.crackit.SpringSecurityJWT.user.Role;
import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private MemberDrugStockRepository memberDrugStockRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Admin kullanıcısını kontrol et ve yoksa ekle
        User admin = userRepository.findByEmail("admin@example.com")
                .orElseGet(() -> {
                    User newAdmin = User.builder()
                            .eczaneAdi("Admin Eczane")
                            .city("İstanbul")
                            .email("admin@example.com")
                            .phoneNumber("05532106873")
                            .password(passwordEncoder.encode("adminpassword")) // Şifre hashleniyor
                            .address("Admin Address")
                            .district("Admin District")
                            .latitude(41.0082)
                            .longitude(28.9784)
                            .role(Role.ADMIN) // Enum doğrudan kullanılabilir
                            .isActive(true) // Admin başlangıçta aktif
                            .build();
                    return userRepository.save(newAdmin);
                });

        // İlaçları ekleyin
        List<Drug> drugs = Arrays.asList(
                new Drug("Magneztest91", "Vitamin7", "Asit"),
                new Drug("Aspirin", "Painkiller", "Acetylsalicylic Acid"),
                new Drug("Tylenol", "Painkiller", "Acetaminophen"),
                new Drug("Amoxicillin", "Antibiotic", "Amoxicillin"),
                new Drug("Zyrtec", "Antihistamine", "Cetirizine")
        );

        drugRepository.saveAll(drugs);

        // Rastgele stok verilerini ekleyin
        Random random = new Random();
        for (Drug drug : drugs) {
            MemberDrugStock stock = new MemberDrugStock(admin, drug, random.nextInt(500) + 1);
            memberDrugStockRepository.save(stock);
        }
    }
}
