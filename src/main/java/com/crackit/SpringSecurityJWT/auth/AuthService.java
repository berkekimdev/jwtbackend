package com.crackit.SpringSecurityJWT.auth;

import com.crackit.SpringSecurityJWT.config.JwtService;
import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// @Service, bu sınıfın bir Spring servis bileşeni olduğunu belirtir.
// Servis bileşenleri, iş mantığını (business logic) içerir.
@Service

// @RequiredArgsConstructor, Lombok tarafından sağlanır ve final alanlar için bir yapıcı (constructor) oluşturur.
// Bu sayede bağımlılık enjeksiyonu (dependency injection) kolaylaştırılır.
@RequiredArgsConstructor
public class AuthService {
    // Kullanıcı veritabanı işlemleri için UserRepository kullanılır.
    private final UserRepository userRepository;
    // JWT token işlemleri için JwtService kullanılır.
    private final JwtService jwtService;
    // Şifreleme işlemleri için PasswordEncoder kullanılır.
    private final PasswordEncoder passwordEncoder;
    // Kimlik doğrulama işlemleri için AuthenticationManager kullanılır.
    private final AuthenticationManager authenticationManager;

    // Kullanıcı kaydı işlemlerini gerçekleştiren metot
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        // Yeni kullanıcı oluşturma
        var user = User.builder()
                .eczaneAdi(registerRequest.getEczaneAdi()) // Eczane adı
                .city(registerRequest.getCity()) // Şehir
                .email(registerRequest.getEmail()) // E-posta
                .password(passwordEncoder.encode(registerRequest.getPassword())) // Şifreyi şifreleme
                .address(registerRequest.getAddress()) // Adres
                .phoneNumber(registerRequest.getPhoneNumber()) // Telefon numarası
                .district(registerRequest.getDistrict()) // İlçe (Yeni eklenen alan)
                .latitude(registerRequest.getLatitude()) // Enlem (Yeni eklenen alan)
                .longitude(registerRequest.getLongitude()) // Boylam (Yeni eklenen alan)
                .role(registerRequest.getRole()) // Rol
                .isActive(false) // Hesap aktif değil olarak başlatma
                .build();

        // Kullanıcıyı veritabanına kaydetme
        var savedUser = userRepository.save(user);

        // Kullanıcı için JWT token oluşturma
        String jwtToken = jwtService.generateToken(user);

        // AuthenticationResponse ile token'ı döndürme
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    // Kullanıcı kimlik doğrulama işlemlerini gerçekleştiren metot
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Kullanıcı bilgilerini doğrulama
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // E-posta ile kullanıcıyı bulma ve aktif olup olmadığını kontrol etme
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı"));

        // Kullanıcı aktif değilse hata fırlatma
        if (!user.isActive()) {
            throw new IllegalStateException("Hesap aktif değil. Lütfen hesabınızın aktifleştirildiğinden emin olun.");
        }

        // Kullanıcı aktifse, JWT token üret ve AuthenticationResponse ile döndür
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
