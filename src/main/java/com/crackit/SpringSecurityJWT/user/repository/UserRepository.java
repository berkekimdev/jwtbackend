package com.crackit.SpringSecurityJWT.user.repository;

import com.crackit.SpringSecurityJWT.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByIsActive(boolean isActive); // Yeni metod eklendi


    // Şehirlerin listesi
    @Query("SELECT DISTINCT u.city FROM User u")
    List<String> findAllCities();

    // Belirli bir şehre ait ilçelerin listesi
    @Query("SELECT DISTINCT u.district FROM User u WHERE u.city = :city")
    List<String> findDistrictsByCity(@Param("city") String city);

    // Şehir ve ilçeye göre kullanıcıları bulma
    List<User> findByCityAndDistrict(String city, String district);

    // Sadece şehre göre kullanıcıları bulma
    List<User> findByCity(String city);

}
