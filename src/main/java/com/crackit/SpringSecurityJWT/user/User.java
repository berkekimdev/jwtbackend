package com.crackit.SpringSecurityJWT.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    private String eczaneAdi;

    private String city;

    private String email;

    private String password;
    private String address;  // Add this line

    private String district;  // Yeni eklenen alan

    private double latitude;  // Yeni eklenen alan
    private double longitude; // Yeni eklenen alan
    private boolean isActive = false;  // Kullanıcının aktif olup olmadığını belirten alan, varsayılan olarak false

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;  // isActive alanına bağlı olarak kullanıcının etkin olup olmadığını kontrol eder
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }



}
