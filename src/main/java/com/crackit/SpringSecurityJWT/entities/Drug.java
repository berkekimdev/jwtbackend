package com.crackit.SpringSecurityJWT.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "drugs")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String ilacAdi;
    private String ilacGrubu;
    private String ilacEtkenMaddesi;
    private int searchCount = 0;
    private LocalDateTime createdAt; // Yeni eklenen alan



    @Transient
    private int totalStock;

    public Drug() {
        // Default constructor
    }

    public Drug(String ilacAdi, String ilacGrubu, String ilacEtkenMaddesi) {
        this.ilacAdi = ilacAdi;
        this.ilacGrubu = ilacGrubu;
        this.ilacEtkenMaddesi = ilacEtkenMaddesi;
        this.searchCount = 0;
        this.createdAt = LocalDateTime.now();
    }

    // Getter and setter methods

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
