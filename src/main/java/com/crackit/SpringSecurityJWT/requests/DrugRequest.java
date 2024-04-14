package com.crackit.SpringSecurityJWT.requests;

public class DrugRequest {
    private String ilacAdi;
    private String ilacGrubu;
    private String ilacEtkenMaddesi;

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
