package com.crackit.SpringSecurityJWT.repositories;

import com.crackit.SpringSecurityJWT.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByIlacGrubu(String ilacGrubu);

    List<Drug> findByIlacAdiStartingWithIgnoreCase(String ilacAdi);
}
