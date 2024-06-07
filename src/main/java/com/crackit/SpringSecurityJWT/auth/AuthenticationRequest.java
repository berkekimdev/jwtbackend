package com.crackit.SpringSecurityJWT.auth;

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
public class AuthenticationRequest {

  // Kullanıcının e-posta adresini temsil eden alan
  private String email;

  // Kullanıcının şifresini temsil eden alan
  private String password;
}
