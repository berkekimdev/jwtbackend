package com.crackit.SpringSecurityJWT.repositories;

import com.crackit.SpringSecurityJWT.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// DrugRepository, JPA repository aracılığıyla Drug varlıkları üzerinde CRUD işlemleri yapar.
public interface DrugRepository extends JpaRepository<Drug, Long> {

    // İlaç grubuna göre ilaçları bulma yöntemi
    List<Drug> findByIlacGrubu(String ilacGrubu);

    // İlaç adı belirli bir harf veya harflerle başlayan ilaçları bulma yöntemi (büyük/küçük harf duyarsız)
    List<Drug> findByIlacAdiStartingWithIgnoreCase(String ilacAdi);

    // İlaç adını içeren ilaçları bulma yöntemi (büyük/küçük harf duyarsız)
    List<Drug> findByIlacAdiContainingIgnoreCase(String ilacAdi);

    // Arama sayısına göre en çok aranan ilk 10 ilacı bulma yöntemi
    List<Drug> findTop10ByOrderBySearchCountDesc();

    // İlaçları oluşturulma zamanına göre sıralayarak ilk 10 ilacı bulma yöntemi
    @Query("SELECT d FROM Drug d ORDER BY d.createdAt ASC")
    List<Drug> findTop10ByOrderByCreatedAtAsc();

    // Etken maddesi belirli bir string'i içeren ilaçları bulma yöntemi (büyük/küçük harf duyarsız)
    List<Drug> findByIlacEtkenMaddesiContainingIgnoreCase(String ilacEtkenMaddesi);
}
