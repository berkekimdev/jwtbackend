package com.crackit.SpringSecurityJWT.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

// @Service, bu sınıfın bir Spring servis bileşeni olduğunu belirtir.
// Servis bileşenleri, iş mantığını (business logic) içerir.
@Service
public class JwtService {

    // JWT oluşturma ve doğrulama işlemleri için kullanılan gizli anahtar
    private static final String SECRET = "9a2f8c4e6b0d71f3e8b925a45747f894a3d6bc70fa8d5e21a15a6d8c3b9a0e7c";

    // Kullanıcı detaylarına göre JWT token oluşturma
    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername()) // Token'ın konusu (subject) olarak kullanıcı adı belirlenir
                .claim("authorities", populateAuthorities(user.getAuthorities())) // Kullanıcının yetkileri token'a eklenir
                .setIssuedAt(new Date(System.currentTimeMillis())) // Token'ın oluşturulma zamanı belirlenir
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token'ın geçerlilik süresi belirlenir (1 gün)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Token, HS256 algoritması kullanılarak imzalanır
                .compact();
    }

    // JWT oluşturma ve doğrulama işlemleri için kullanılan anahtarı elde etme
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET); // Anahtar BASE64 ile kodlanmış olarak elde edilir
        return Keys.hmacShaKeyFor(keyBytes); // HMAC SHA256 algoritması için anahtar oluşturulur
    }

    // Kullanıcının yetkilerini bir String olarak birleştirme
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            authoritiesSet.add(authority.getAuthority()); // Yetkiler set'e eklenir
        }
        return String.join(",", authoritiesSet); // Yetkiler virgülle ayrılmış bir String olarak döndürülür
    }

    // JWT token'dan tüm claim'leri çıkarma
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey()) // İmzalama anahtarı belirlenir
                .build()
                .parseClaimsJws(token) // Token parse edilir
                .getBody(); // Claim'ler elde edilir
    }

    // JWT token'dan kullanıcı adını (username) çıkarma
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // Claim'lerden token'ın konusunu (subject) çıkarır
    }

    // JWT token'dan belirli bir claim'i çıkarma
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Token'dan tüm claim'leri çıkarır
        return claimsResolver.apply(claims); // Belirli bir claim'i elde etmek için claimsResolver fonksiyonunu kullanır
    }

    // JWT token'ın geçerli olup olmadığını kontrol etme
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Token'dan kullanıcı adını çıkarır
        return (username.equals(userDetails.getUsername())); // Token'daki kullanıcı adı ile sağlanan kullanıcı adı eşleşiyorsa geçerlidir
    }
}
