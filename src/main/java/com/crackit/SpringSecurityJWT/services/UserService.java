package com.crackit.SpringSecurityJWT.services;

import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service, bu sınıfın bir servis bileşeni olduğunu belirtir ve Spring tarafından yönetilir.
@Service
public class UserService {

    // UserRepository, kullanıcı veritabanı işlemleri için kullanılır.
    @Autowired
    private UserRepository userRepository;

    // PasswordEncoder, şifreleri hashlemek için kullanılır.
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Kullanıcı aktiflik durumunu güncelleme işlemi
    public void updateUserActiveStatus(Integer id, boolean isActive) {
        // Belirtilen ID'ye sahip kullanıcıyı bul
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Kullanıcının aktiflik durumunu güncelle
        user.setActive(isActive);

        // Güncellenmiş kullanıcıyı kaydet
        userRepository.save(user);
    }

    // Kullanıcı bilgilerini güncelleme işlemi
    public User updateUser(Integer id, User userRequest) {
        // Belirtilen ID'ye sahip kullanıcıyı bul
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Temel bilgileri güncelle
        user.setEczaneAdi(userRequest.getEczaneAdi());
        user.setCity(userRequest.getCity());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setDistrict(userRequest.getDistrict());
        user.setLatitude(userRequest.getLatitude());
        user.setLongitude(userRequest.getLongitude());
        user.setActive(userRequest.isActive());
        user.setRole(userRequest.getRole());

        // Şifreyi hashlenmiş olarak sakla
        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }

        // Güncellenmiş kullanıcı bilgilerini kaydet
        return userRepository.save(user);
    }

    // Aktif kullanıcıları bulma işlemi
    public List<User> findActiveUsers() {
        return userRepository.findByIsActive(true);
    }

    // Kullanıcı şifresini güncelleme işlemi
    public void updatePassword(Integer id, String newPassword) {
        // Belirtilen ID'ye sahip kullanıcıyı bul
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Yeni şifreyi hashleyerek güncelle
        user.setPassword(passwordEncoder.encode(newPassword));

        // Güncellenmiş kullanıcıyı kaydet
        userRepository.save(user);
    }

    // Kullanıcıyı silme işlemi
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    // Tüm kullanıcıları bulma işlemi
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Belirli bir ID'ye sahip kullanıcıyı bulma işlemi
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Tüm şehirleri bulma işlemi
    public List<String> findAllCities() {
        return userRepository.findAllCities();
    }

    // Belirli bir şehre ait ilçeleri bulma işlemi
    public List<String> findDistrictsByCity(String city) {
        return userRepository.findDistrictsByCity(city);
    }

    // Belirli bir şehir ve ilçeye ait kullanıcıları bulma işlemi
    public List<User> findUsersByCityAndDistrict(String city, String district) {
        if (district == null || district.isEmpty()) {
            return userRepository.findByCity(city);
        } else {
            return userRepository.findByCityAndDistrict(city, district);
        }
    }
}
