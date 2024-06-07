package com.crackit.SpringSecurityJWT.auth;

// Kullanıcının aktif/pasif durumunu temsil eden sınıf
public class UserActiveRequest {

    // Kullanıcının aktif olup olmadığını belirten boolean değişken
    private boolean active;

    // Kullanıcının aktif durumunu döndüren getter metodu
    public boolean isActive() {
        return active;
    }

    // Kullanıcının aktif durumunu ayarlayan setter metodu
    public void setActive(boolean active) {
        this.active = active;
    }
}
