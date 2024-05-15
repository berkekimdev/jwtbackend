package com.crackit.SpringSecurityJWT.auth;

import com.crackit.SpringSecurityJWT.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String eczaneAdi;
    private String city;
    private String email;
    private String password;
    private String address;
    private String district; // Yeni eklenen alan
    private double latitude; // Yeni eklenen alan
    private double longitude; /// Add this line
    private Role role;
    private String phoneNumber;
}
