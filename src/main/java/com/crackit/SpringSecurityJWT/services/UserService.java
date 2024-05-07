package com.crackit.SpringSecurityJWT.services;

import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void updateUserActiveStatus(Integer id, boolean isActive) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setActive(isActive);  // isActive alanını güncelle
        userRepository.save(user);  // Güncellenmiş kullanıcıyı kaydet
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Integer id) {  // ID türünü Integer olarak güncelledik
        return userRepository.findById(id).orElse(null);
    }
}
