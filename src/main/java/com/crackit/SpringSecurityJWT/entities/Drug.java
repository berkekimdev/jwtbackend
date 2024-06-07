package com.crackit.SpringSecurityJWT.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// @Entity, bu sınıfın bir JPA varlığı olduğunu belirtir. Bu sınıf, veritabanı tablosuna karşılık gelir.
@Entity

// @Table, bu varlığın karşılık geldiği veritabanı tablosunu belirtir.
// Bu durumda, "drugs" adlı tabloya karşılık gelir.
@Table(name = "drugs")
public class Drug {

    // @Id, bu alanın birincil anahtar olduğunu belirtir.
    @Id
    // @GeneratedValue, birincil anahtarın nasıl üretileceğini belirtir.
    // GenerationType.IDENTITY, otomatik artan birincil anahtar kullanır.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column, bu alanın veritabanı sütununu belirtir ve unique=true, bu sütunun benzersiz olması gerektiğini belirtir.
    @Column(unique = true)
    private String ilacAdi;

    private String ilacGrubu;
    private String ilacEtkenMaddesi;

    // Arama sayısını tutan alan, varsayılan değeri 0 olarak ayarlanmıştır.
    private int searchCount = 0;

    // İlaç oluşturulma zamanını tutan alan, yeni eklenmiştir.
    private LocalDateTime createdAt;

    // @Transient, bu alanın veritabanında bir sütun olarak saklanmaması gerektiğini belirtir.
    @Transient
    private int totalStock;

    // Varsayılan yapıcı (constructor)
    public Drug() {
        // Default constructor
    }

    // Parametreli yapıcı, ilaç adı, grubu ve etken maddesi ile yeni bir ilaç nesnesi oluşturur.
    public Drug(String ilacAdi, String ilacGrubu, String ilacEtkenMaddesi) {
        this.ilacAdi = ilacAdi;
        this.ilacGrubu = ilacGrubu;
        this.ilacEtkenMaddesi = ilacEtkenMaddesi;
        this.searchCount = 0;
        this.createdAt = LocalDateTime.now();
    }

    // Getter ve setter metodları

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIlacAdi() {
        return ilacAdi;
    }

    public void setIlacAdi(String ilacAdi) {
        this.ilacAdi = ilacAdi;
    }

    public String getIlacGrubu() {
        return ilacGrubu;
    }

    public void setIlacGrubu(String ilacGrubu) {
        this.ilacGrubu = ilacGrubu;
    }

    public String getIlacEtkenMaddesi() {
        return ilacEtkenMaddesi;
    }

    public void setIlacEtkenMaddesi(String ilacEtkenMaddesi) {
        this.ilacEtkenMaddesi = ilacEtkenMaddesi;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
