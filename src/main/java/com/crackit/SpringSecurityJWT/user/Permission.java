package com.crackit.SpringSecurityJWT.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor, Lombok tarafından otomatik olarak sınıfın tüm final alanları için bir constructor oluşturur.
@RequiredArgsConstructor
public enum Permission {

    // Admin'in okuma izni
    ADMIN_READ("admin:read"),

    // Admin'in oluşturma izni
    ADMIN_CREATE("admin:create"),

    // Üyenin okuma izni
    MEMBER_READ("management:read"),

    // Üyenin oluşturma izni
    MEMBER_CREATE("management:create");

    // İzin adı
    @Getter
    private final String permission;
}
