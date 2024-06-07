package com.crackit.SpringSecurityJWT.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.crackit.SpringSecurityJWT.user.Permission.*;

// @RequiredArgsConstructor, Lombok tarafından otomatik olarak sınıfın tüm final alanları için bir constructor oluşturur.
@RequiredArgsConstructor
public enum Role {

  // Admin rolü ve ilgili izinler
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_CREATE,
                  MEMBER_READ,
                  MEMBER_CREATE
          )
  ),

  // Üye rolü ve ilgili izinler
  MEMBER(
          Set.of(
                  MEMBER_READ,
                  MEMBER_CREATE
          )
  );

  // Her rol için izin seti
  @Getter
  private final Set<Permission> permissions;

  // Rol için yetki listesi döndüren metod
  public List<SimpleGrantedAuthority> getAuthorities() {
    // İzinlerden yetki listesi oluşturma
    var authorities = getPermissions()
            .stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
            .collect(Collectors.toList());

    // Rolün kendisini yetki listesine ekleme
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
