package com.crackit.SpringSecurityJWT.entities;

import jakarta.persistence.*;



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

    @Transient
    private int totalStock;
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
    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

}
