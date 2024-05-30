package com.crackit.SpringSecurityJWT.repositories;

import com.crackit.SpringSecurityJWT.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByIlacGrubu(String ilacGrubu);

    List<Drug> findByIlacAdiStartingWithIgnoreCase(String ilacAdi);

    List<Drug> findByIlacAdiContainingIgnoreCase(String ilacAdi);

    List<Drug> findTop10ByOrderBySearchCountDesc();

    @Query("SELECT d FROM Drug d ORDER BY d.createdAt ASC")
    List<Drug> findTop10ByOrderByCreatedAtAsc();

    List<Drug> findByIlacEtkenMaddesiContainingIgnoreCase(String ilacEtkenMaddesi);

}
