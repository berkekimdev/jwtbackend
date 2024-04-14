package com.crackit.SpringSecurityJWT.responses;

public class DrugResponse {
    private Long id;
    private String ilacAdi;
    private String ilacGrubu;
    private String ilacEtkenMaddesi;

    public DrugResponse(Long id, String ilacAdi, String ilacGrubu, String ilacEtkenMaddesi) {
        this.id = id;
        this.ilacAdi = ilacAdi;
        this.ilacGrubu = ilacGrubu;
        this.ilacEtkenMaddesi = ilacEtkenMaddesi;
    }

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
