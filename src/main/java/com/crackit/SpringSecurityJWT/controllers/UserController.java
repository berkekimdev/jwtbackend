package com.crackit.SpringSecurityJWT.controllers;

import com.crackit.SpringSecurityJWT.auth.UserActiveRequest;
import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Tüm kullanıcıları döndür
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PatchMapping("/{id}/activate")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> updateUserActiveStatus(@PathVariable Integer id, @RequestBody UserActiveRequest request) {
        userService.updateUserActiveStatus(id, request.isActive());
        return ResponseEntity.ok("User active status updated to: " + request.isActive());
    }


    // Belirli bir kullanıcının detaylarını döndür
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {  // ID türünü Integer olarak güncelledik
        return userService.findUserById(id);
    }
}
