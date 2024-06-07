package com.crackit.SpringSecurityJWT.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// @Component, bu sınıfın bir Spring bileşeni olduğunu belirtir.
// Bu sayede Spring, bu sınıfın bir örneğini otomatik olarak oluşturur ve yönetir.
@Component

// @RequiredArgsConstructor, Lombok tarafından sağlanır ve final alanlar için
// bir yapıcı (constructor) oluşturur. Bu, bağımlılık enjeksiyonunu kolaylaştırır.
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    // JWT işlemleri için JwtService kullanılır.
    private final JwtService jwtService;
    // Kullanıcı detaylarını yüklemek için UserDetailsService kullanılır.
    private final UserDetailsService userDetailsService;

    // HTTP isteklerini filtrelemek için kullanılan metod.
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // İstek başlığında Authorization başlığının olup olmadığını ve Bearer ile başlayıp başlamadığını doğrula
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // JWT'yi Authorization başlığından çıkar
        jwt = authHeader.substring(7);
        // Kullanıcıyı veritabanında doğrula ve tokenın geçerliliğini kontrol et
        email = jwtService.extractUsername(jwt);
        // Kullanıcı mevcutsa ve SecurityContext'te authentication nesnesi yoksa
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            // Kullanıcı geçerliyse SecurityContextHolder'a ayarla
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Filtre zincirine devam et
        filterChain.doFilter(request, response);
    }

    // Belirli yolların filtrelenmemesini sağlar
    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("/crackit/v1/auth");
    }
}
