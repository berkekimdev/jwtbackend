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
import java.util.Set;
import java.util.stream.Collectors;

// @Component, bu sınıfın bir Spring bileşeni olduğunu belirtir ve Spring tarafından yönetilir.
@Component
public class DatabaseSeeder implements CommandLineRunner {

    // DrugRepository, ilaç veritabanı işlemleri için kullanılır.
    @Autowired
    private DrugRepository drugRepository;

    // MemberDrugStockRepository, üye ilaç stoğu veritabanı işlemleri için kullanılır.
    @Autowired
    private MemberDrugStockRepository memberDrugStockRepository;

    // UserRepository, kullanıcı veritabanı işlemleri için kullanılır.
    @Autowired
    private UserRepository userRepository;

    // PasswordEncoder, şifreleri hashlemek için kullanılır.
    @Autowired
    private PasswordEncoder passwordEncoder;

    // run metodu, uygulama başlatıldığında çalıştırılır ve veritabanını tohumlar.
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

        // Mevcut ilaç adlarını alın
        Set<String> existingDrugNames = drugRepository.findAll().stream()
                .map(Drug::getIlacAdi)
                .collect(Collectors.toSet());

        // Yeni ilaç listesi oluşturun
        List<Drug> drugs = Arrays.asList(
                new Drug("Parol", "Ağrı Kesici", "Parasetamol"),
                new Drug("Majezik", "Ağrı Kesici", "Flurbiprofen"),
                new Drug("Dolorex", "Ağrı Kesici", "Ketoprofen"),
                new Drug("Nurofen", "Ağrı Kesici", "İbuprofen"),
                new Drug("Novalgin", "Ağrı Kesici", "Metamizol Sodyum"),
                new Drug("Arveles", "Ağrı Kesici", "Deksketoprofen"),
                new Drug("Aferin", "Soğuk Algınlığı", "Parasetamol, Klorfeniramin Maleat"),
                new Drug("Gripin", "Soğuk Algınlığı", "Parasetamol, Kafein"),
                new Drug("Coraspin", "Kan Sulandırıcı", "Asetilsalisilik Asit"),
                new Drug("Coumadin", "Kan Sulandırıcı", "Varfarin"),
                new Drug("Plavix", "Kan Sulandırıcı", "Klopidogrel"),
                new Drug("Eliquis", "Kan Sulandırıcı", "Apiksaban"),
                new Drug("Xarelto", "Kan Sulandırıcı", "Rivaroksaban"),
                new Drug("Pariet", "Mide İlacı", "Rabeprazol"),
                new Drug("Nexium", "Mide İlacı", "Esomeprazol"),
                new Drug("Lansor", "Mide İlacı", "Lansoprazol"),
                new Drug("Losec", "Mide İlacı", "Omeprazol"),
                new Drug("Pantpas", "Mide İlacı", "Pantoprazol"),
                new Drug("Zantac", "Mide İlacı", "Ranitidin"),
                new Drug("Deksilant", "Mide İlacı", "Dekslansoprazol"),
                new Drug("Zyban", "Sigara Bırakma", "Bupropion"),
                new Drug("Champix", "Sigara Bırakma", "Vareniklin"),
                new Drug("Wellbutrin", "Depresyon", "Bupropion"),
                new Drug("Nicotinell", "Sigara Bırakma", "Nikotin"),
                new Drug("Nicorette", "Sigara Bırakma", "Nikotin"),
                new Drug("Zestril", "Tansiyon", "Lisinopril"),
                new Drug("Norvasc", "Tansiyon", "Amlodipin"),
                new Drug("Beloc", "Tansiyon", "Metoprolol"),
                new Drug("Diovan", "Tansiyon", "Valsartan"),
                new Drug("Micardis", "Tansiyon", "Telmisartan"),
                new Drug("Tensiomin", "Tansiyon", "Kaptopril"),
                new Drug("Dideral", "Tansiyon", "Propranolol"),
                new Drug("Atacand", "Tansiyon", "Kandesartan"),
                new Drug("Olmetec", "Tansiyon", "Olmesartan"),
                new Drug("Crestor", "Kolesterol", "Rosuvastatin"),
                new Drug("Lipitor", "Kolesterol", "Atorvastatin"),
                new Drug("Zocor", "Kolesterol", "Simvastatin"),
                new Drug("Tylol Hot", "Soğuk Algınlığı", "Parasetamol"),
                new Drug("Fervex", "Soğuk Algınlığı", "Parasetamol, Askorbik Asit"),
                new Drug("Calpol", "Ağrı Kesici", "Parasetamol"),
                new Drug("Ibucold", "Soğuk Algınlığı", "İbuprofen, Psödoefedrin"),
                new Drug("Sudafed", "Soğuk Algınlığı", "Psödoefedrin"),
                new Drug("Panadol", "Ağrı Kesici", "Parasetamol"),
                new Drug("Tylol", "Ağrı Kesici", "Parasetamol"),
                new Drug("Minoset", "Ağrı Kesici", "Parasetamol"),
                new Drug("Theraflu", "Soğuk Algınlığı", "Parasetamol, Fenilefrin"),
                new Drug("Otrivine", "Soğuk Algınlığı", "Ksiloematazolin"),
                new Drug("Orofar", "Boğaz Ağrısı", "Benzydamin HCl"),
                new Drug("Faringosept", "Boğaz Ağrısı", "Ambazon"),
                new Drug("Tantum Verde", "Boğaz Ağrısı", "Benzydamin HCl"),
                new Drug("Voltaren", "Ağrı Kesici", "Diklofenak"),
                new Drug("Fastjel", "Ağrı Kesici", "Ketoprofen"),
                new Drug("Dolgit", "Ağrı Kesici", "İbuprofen"),
                new Drug("Lyrica", "Nöropatik Ağrı", "Pregabalin"),
                new Drug("Cymbalta", "Nöropatik Ağrı", "Duloksetin"),
                new Drug("Neurontin", "Nöropatik Ağrı", "Gabapentin"),
                new Drug("Gabateva", "Nöropatik Ağrı", "Gabapentin"),
                new Drug("Pregabalin", "Nöropatik Ağrı", "Pregabalin"),
                new Drug("Dilatrend", "Kalp İlacı", "Kardevilol"),
                new Drug("Yasmin", "Doğum Kontrol İlacı", "Drospirenon"),
                new Drug("Concor", "Kalp İlacı", "Bisoprolol"),
                new Drug("Vasoxen", "Kalp İlacı", "Nebivolol"),
                new Drug("Natrilix", "Tansiyon", "Indapamid"),
                new Drug("Co-Diovan", "Tansiyon", "Valsartan, Hidroklorotiazid"),
                new Drug("Exforge", "Tansiyon", "Amlodipin, Valsartan"),
                new Drug("Tenox", "Tansiyon", "Amlodipin"),
                new Drug("Micardis Plus", "Tansiyon", "Telmisartan, Hidroklorotiazid"),
                new Drug("Nolvadex", "Onkoloji", "Tamoksifen"),
                new Drug("Femara", "Onkoloji", "Letrozol"),
                new Drug("Zoladex", "Onkoloji", "Goserelin"),
                new Drug("Arimidex", "Onkoloji", "Anastrozol"),
                new Drug("Xeloda", "Onkoloji", "Capecitabine"),
                new Drug("Tarceva", "Onkoloji", "Erlotinib"),
                new Drug("Iressa", "Onkoloji", "Gefitinib"),
                new Drug("Glivec", "Onkoloji", "Imatinib"),
                new Drug("Avastin", "Onkoloji", "Bevacizumab"),
                new Drug("Eloxatin", "Onkoloji", "Oksaliplatin"),
                new Drug("Advil", "Ağrı Kesici", "İbuprofen"),
                new Drug("Motrin", "Ağrı Kesici", "İbuprofen"),
                new Drug("Aleve", "Ağrı Kesici", "Naproksen"),
                new Drug("Naproxen", "Ağrı Kesici", "Naproksen"),
                new Drug("Celebrex", "Ağrı Kesici", "Selecoxib"),
                new Drug("Cataflam", "Ağrı Kesici", "Diklofenak"),
                new Drug("Feldene", "Ağrı Kesici", "Piroksikam"),
                new Drug("Toradol", "Ağrı Kesici", "Ketorolak"),
                new Drug("Mobic", "Ağrı Kesici", "Meloksikam"),
                new Drug("Relafen", "Ağrı Kesici", "Nabumeton"),
                new Drug("Indocin", "Ağrı Kesici", "İndometasin"),
                new Drug("Orudis", "Ağrı Kesici", "Ketoprofen"),
                new Drug("Anaprox", "Ağrı Kesici", "Naproksen"),
                new Drug("Daypro", "Ağrı Kesici", "Oksaprozin")
        );

        // Veritabanında mevcut olmayan ilaçları filtreleyin
        List<Drug> uniqueDrugs = drugs.stream()
                .filter(drug -> !existingDrugNames.contains(drug.getIlacAdi()))
                .collect(Collectors.toList());

        // Yeni ilaçları veritabanına kaydedin
        drugRepository.saveAll(uniqueDrugs);

        // 10 eczane ekleyin
        List<User> users = Arrays.asList(
                User.builder()
                        .eczaneAdi("Eczane 1")
                        .city("Isparta")
                        .email("eczane1@example.com")
                        .phoneNumber("05532106871")
                        .password(passwordEncoder.encode("password1"))
                        .address("Address 1")
                        .district("District 1")
                        .latitude(37.7648)
                        .longitude(30.5566)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build(),
                User.builder()
                        .eczaneAdi("Eczane 2")
                        .city("Isparta")
                        .email("eczane2@example.com")
                        .phoneNumber("05532106872")
                        .password(passwordEncoder.encode("password2"))
                        .address("Address 2")
                        .district("District 2")
                        .latitude(37.7619)
                        .longitude(30.5550)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build(),
                User.builder()
                        .eczaneAdi("Eczane 3")
                        .city("Isparta")
                        .email("eczane3@example.com")
                        .phoneNumber("05532106873")
                        .password(passwordEncoder.encode("password3"))
                        .address("Address 3")
                        .district("District 3")
                        .latitude(37.7642)
                        .longitude(30.5529)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build(),
                User.builder()
                        .eczaneAdi("Eczane 4")
                        .city("Isparta")
                        .email("eczane4@example.com")
                        .phoneNumber("05532106874")
                        .password(passwordEncoder.encode("password4"))
                        .address("Address 4")
                        .district("District 4")
                        .latitude(37.7672)
                        .longitude(30.5558)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build(),
                User.builder()
                        .eczaneAdi("Eczane 5")
                        .city("İstanbul")
                        .email("eczane5@example.com")
                        .phoneNumber("05532106875")
                        .password(passwordEncoder.encode("password5"))
                        .address("Address 5")
                        .district("District 5")
                        .latitude(41.0082)
                        .longitude(28.9784)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build(),
                User.builder()
                        .eczaneAdi("Eczane 6")
                        .city("Ankara")
                        .email("eczane6@example.com")
                        .phoneNumber("05532106876")
                        .password(passwordEncoder.encode("password6"))
                        .address("Address 6")
                        .district("District 6")
                        .latitude(39.9334)
                        .longitude(32.8597)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build(),
                User.builder()
                        .eczaneAdi("Eczane 7")
                        .city("İzmir")
                        .email("eczane7@example.com")
                        .phoneNumber("05532106877")
                        .password(passwordEncoder.encode("password7"))
                        .address("Address 7")
                        .district("District 7")
                        .latitude(38.4237)
                        .longitude(27.1428)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build(),
                User.builder()
                        .eczaneAdi("Eczane 8")
                        .city("Bursa")
                        .email("eczane8@example.com")
                        .phoneNumber("05532106878")
                        .password(passwordEncoder.encode("password8"))
                        .address("Address 8")
                        .district("District 8")
                        .latitude(40.1828)
                        .longitude(29.0665)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build(),
                User.builder()
                        .eczaneAdi("Eczane 9")
                        .city("Antalya")
                        .email("eczane9@example.com")
                        .phoneNumber("05532106879")
                        .password(passwordEncoder.encode("password9"))
                        .address("Address 9")
                        .district("District 9")
                        .latitude(36.8969)
                        .longitude(30.7133)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build(),
                User.builder()
                        .eczaneAdi("Eczane 10")
                        .city("Adana")
                        .email("eczane10@example.com")
                        .phoneNumber("05532106880")
                        .password(passwordEncoder.encode("password10"))
                        .address("Address 10")
                        .district("District 10")
                        .latitude(37.0)
                        .longitude(35.3213)
                        .role(Role.MEMBER)
                        .isActive(true)
                        .build()
        );

        // Yeni kullanıcıları veritabanına kaydedin
        userRepository.saveAll(users);

        // Rastgele stok verilerini ekleyin
        Random random = new Random();
        for (User user : users) {
            for (Drug drug : uniqueDrugs) {
                MemberDrugStock stock = new MemberDrugStock(user, drug, random.nextInt(40) + 1);
                memberDrugStockRepository.save(stock);
            }
        }
    }
}
