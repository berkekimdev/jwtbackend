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

@Service
@RequiredArgsConstructor
public class AuthService {
    private  final UserRepository userRepository;
    private  final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .eczaneAdi(registerRequest.getEczaneAdi())
                .city(registerRequest.getCity()) // lastName to city
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .address(registerRequest.getAddress())
                .district(registerRequest.getDistrict()) // Yeni eklenen alan
                .latitude(registerRequest.getLatitude()) // Yeni eklenen alan
                .longitude(registerRequest.getLongitude()) // Yeni eklenen alan
                .role(registerRequest.getRole())
                .isActive(false)
                .build();
        var savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Kullanıcı bilgilerini doğrulama
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // E-posta ile kullanıcıyı bulma ve aktif olup olmadığını kontrol etme
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı"));

        if (!user.isActive()) {
            throw new IllegalStateException("Hesap aktif değil. Lütfen hesabınızın aktifleştirildiğinden emin olun.");
        }

        // Kullanıcı aktifse, JWT token üret ve dön
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}

