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

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userRequest) {
        User updatedUser = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }



    @PatchMapping("/{id}/activate")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> updateUserActiveStatus(@PathVariable Integer id, @RequestBody UserActiveRequest request) {
        userService.updateUserActiveStatus(id, request.isActive());
        return ResponseEntity.ok("User active status updated to: " + request.isActive());
    }


    @PatchMapping("/{id}/password")
    public ResponseEntity<String> updatePassword(@PathVariable Integer id, @RequestBody String newPassword) {
        userService.updatePassword(id, newPassword);
        return ResponseEntity.ok("Password updated successfully.");
    }


    // Belirli bir kullanıcının detaylarını döndür
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {  // ID türünü Integer olarak güncelledik
        return userService.findUserById(id);
    }
}
