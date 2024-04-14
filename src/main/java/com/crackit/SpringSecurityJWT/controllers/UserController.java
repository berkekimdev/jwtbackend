package com.crackit.SpringSecurityJWT.controllers;

import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    // Belirli bir kullanıcının detaylarını döndür
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {  // ID türünü Integer olarak güncelledik
        return userService.findUserById(id);
    }
}
