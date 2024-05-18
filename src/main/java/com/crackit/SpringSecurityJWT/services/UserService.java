package com.crackit.SpringSecurityJWT.services;

import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void updateUserActiveStatus(Integer id, boolean isActive) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setActive(isActive);  // isActive alanını güncelle
        userRepository.save(user);  // Güncellenmiş kullanıcıyı kaydet
    }


    public User updateUser(Integer id, User userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Temel bilgiler
        user.setEczaneAdi(userRequest.getEczaneAdi());
        user.setCity(userRequest.getCity());
        user.setEmail(userRequest.getEmail());// Şifre, hashlenmiş olarak saklanmalıdır
        user.setAddress(userRequest.getAddress());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setDistrict(userRequest.getDistrict());
        user.setLatitude(userRequest.getLatitude());
        user.setLongitude(userRequest.getLongitude());
        user.setActive(userRequest.isActive());
        user.setRole(userRequest.getRole());  // Rol bilgisi, EnumType olarak saklanıyor
        // Şifre, hashlenmiş olarak saklanmalıdır
        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }

        // Veritabanında güncellenmiş kullanıcı bilgilerini kaydet
        return userRepository.save(user);
    }


    public void updatePassword(Integer id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setPassword(newPassword);  // Yeni şifreyi şifreleme ile güncelle
        userRepository.save(user);
    }
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Integer id) {  // ID türünü Integer olarak güncelledik
        return userRepository.findById(id).orElse(null);
    }
}
