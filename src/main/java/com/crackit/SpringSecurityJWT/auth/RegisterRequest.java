package com.crackit.SpringSecurityJWT.auth;

import com.crackit.SpringSecurityJWT.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data, Lombok kütüphanesi tarafından sağlanır ve bu sınıf için getter, setter, toString, equals ve hashCode metodlarını otomatik olarak oluşturur.
@Data

// @Builder, Lombok kütüphanesi tarafından sağlanır ve bu sınıf için Builder deseni kullanılarak nesne oluşturmayı sağlar.
@Builder

// @AllArgsConstructor, Lombok kütüphanesi tarafından sağlanır ve bu sınıf için tüm alanları içeren bir yapıcı (constructor) oluşturur.
@AllArgsConstructor

// @NoArgsConstructor, Lombok kütüphanesi tarafından sağlanır ve bu sınıf için parametresiz bir yapıcı (constructor) oluşturur.
@NoArgsConstructor
public class RegisterRequest {
    // Kullanıcının eczane adını temsil eden alan
    private String eczaneAdi;

    // Kullanıcının yaşadığı şehri temsil eden alan
    private String city;

    // Kullanıcının e-posta adresini temsil eden alan
    private String email;

    // Kullanıcının şifresini temsil eden alan
    private String password;

    // Kullanıcının adresini temsil eden alan
    private String address;

    // Kullanıcının yaşadığı ilçeyi temsil eden alan
    private String district;

    // Kullanıcının enlem bilgisini temsil eden alan
    private double latitude;

    // Kullanıcının boylam bilgisini temsil eden alan
    private double longitude;

    // Kullanıcının rolünü temsil eden alan
    private Role role;

    // Kullanıcının telefon numarasını temsil eden alan
    private String phoneNumber;
}
