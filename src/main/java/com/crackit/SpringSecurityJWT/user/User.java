package com.crackit.SpringSecurityJWT.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// @Entity, bu sınıfın bir JPA varlığı olduğunu belirtir.
@Entity

// @Table, veritabanında bu varlığa karşılık gelen tablo adını belirtir.
@Table(name = "_user")

// Lombok anotasyonları, kodu basitleştirmek ve tekrarları azaltmak için kullanılır.
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    // @Id, bu alanın varlık kimliği olduğunu belirtir.
    @Id

    // @GeneratedValue, kimlik değerinin otomatik olarak oluşturulacağını belirtir.
    @GeneratedValue
    private Integer id;

    // @Column(unique = true), bu sütunun veritabanında benzersiz olması gerektiğini belirtir.
    @Column(unique = true)
    private String eczaneAdi;

    private String city;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;  // Adres alanı

    private String district;  // İlçe alanı

    private double latitude;  // Enlem alanı
    private double longitude; // Boylam alanı

    // Kullanıcının aktif olup olmadığını belirten alan, varsayılan olarak false
    private boolean isActive = false;

    // @Enumerated(EnumType.STRING), bu alanın enum olarak saklanacağını ve veritabanında string olarak tutulacağını belirtir.
    @Enumerated(EnumType.STRING)
    private Role role;

    // Getter ve Setter metodları Lombok tarafından otomatik olarak oluşturulur
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // getAuthorities metodu, kullanıcının sahip olduğu yetkileri döner.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    // getUsername metodu, kullanıcı adını (bu durumda e-posta) döner.
    @Override
    public String getUsername() {
        return email;
    }

    // Kullanıcı hesabının süresinin dolup dolmadığını kontrol eder (bu örnekte her zaman true döner).
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Kullanıcı hesabının kilitli olup olmadığını kontrol eder (bu örnekte her zaman true döner).
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Kullanıcı kimlik bilgilerinin süresinin dolup dolmadığını kontrol eder (bu örnekte her zaman true döner).
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Kullanıcının etkin olup olmadığını kontrol eder.
    @Override
    public boolean isEnabled() {
        return this.isActive;
    }

    // Kullanıcının aktif olup olmadığını belirten alanı günceller
    public void setActive(boolean active) {
        this.isActive = active;
    }

    public String getEczaneAdi() {
        return eczaneAdi;
    }

    public void setEczaneAdi(String eczaneAdi) {
        this.eczaneAdi = eczaneAdi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isActive() {
        return isActive;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
