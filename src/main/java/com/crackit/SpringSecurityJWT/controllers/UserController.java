package com.crackit.SpringSecurityJWT.controllers;

import com.crackit.SpringSecurityJWT.auth.UserActiveRequest;
import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.services.UserService;
import com.crackit.SpringSecurityJWT.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController, bu sınıfın bir Spring MVC denetleyicisi olduğunu belirtir.
// Bu, HTTP isteklerini işlemek için kullanılır.
@RestController
@CrossOrigin
// @RequestMapping, bu denetleyicinin tüm yollarının "/api/users" ile başlaması gerektiğini belirtir.
@RequestMapping("/api/users")
public class UserController {

    // UserRepository, kullanıcı veritabanı işlemleri için kullanılır.
    @Autowired
    private UserRepository userRepository;

    // UserService, kullanıcı işlemleri için iş mantığını içerir.
    @Autowired
    private UserService userService;

    // Tüm kullanıcıları döndüren endpoint
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // Belirli bir kullanıcıyı güncelleyen endpoint
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userRequest) {
        User updatedUser = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    // Belirli bir kullanıcıyı silen endpoint
    @DeleteMapping("/{userId}")
    @Secured("ROLE_ADMIN") // Bu endpoint'e sadece ADMIN rolüne sahip kullanıcılar erişebilir
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Kullanıcıyı aktif veya pasif yapan endpoint
    @PatchMapping("/{id}/activate")
    @Secured("ROLE_ADMIN") // Bu endpoint'e sadece ADMIN rolüne sahip kullanıcılar erişebilir
    public ResponseEntity<String> updateUserActiveStatus(@PathVariable Integer id, @RequestBody UserActiveRequest request) {
        userService.updateUserActiveStatus(id, request.isActive());
        return ResponseEntity.ok("User active status updated to: " + request.isActive());
    }

    // Sadece aktif kullanıcıları döndüren endpoint
    @GetMapping("/active")
    public List<User> getActiveUsers() {
        return userService.findActiveUsers();
    }

    // Kullanıcıyı kullanıcı adına göre döndüren endpoint
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Kullanıcının şifresini güncelleyen endpoint
    @PatchMapping("/{id}/password")
    public ResponseEntity<String> updatePassword(@PathVariable Integer id, @RequestBody String newPassword) {
        userService.updatePassword(id, newPassword);
        return ResponseEntity.ok("Password updated successfully.");
    }

    // Belirli bir kullanıcının detaylarını döndüren endpoint
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    // Şehirlerin listesini getiren endpoint
    @GetMapping("/cities")
    public ResponseEntity<List<String>> getAllCities() {
        List<String> cities = userService.findAllCities();
        return ResponseEntity.ok(cities);
    }

    // Belirli bir şehre ait ilçelerin listesini getiren endpoint
    @GetMapping("/districts")
    public ResponseEntity<List<String>> getDistrictsByCity(@RequestParam String city) {
        List<String> districts = userService.findDistrictsByCity(city);
        return ResponseEntity.ok(districts);
    }

    // Şehir ve ilçeye göre kullanıcıları getiren endpoint
    @GetMapping("/search")
    public ResponseEntity<List<User>> getUsersByCityAndDistrict(
            @RequestParam String city,
            @RequestParam(required = false) String district) {
        List<User> users = userService.findUsersByCityAndDistrict(city, district);
        return ResponseEntity.ok(users);
    }
}
