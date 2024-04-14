package com.crackit.SpringSecurityJWT.entities;

import jakarta.persistence.*;



@Entity
@Table(name = "drugs")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ilacAdi;
    private String ilacGrubu;
    private String ilacEtkenMaddesi;

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
}
