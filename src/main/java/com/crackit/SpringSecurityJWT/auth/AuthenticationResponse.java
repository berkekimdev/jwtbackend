package com.crackit.SpringSecurityJWT.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AuthenticationResponse {

    // @JsonProperty, bu alanın JSON serileştirme ve serileştirme sırasında "access_token" olarak adlandırılmasını sağlar.
    @JsonProperty("access_token")
    // Kullanıcının erişim token'ını temsil eden alan
    private String accessToken;
}
