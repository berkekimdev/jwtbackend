package com.crackit.SpringSecurityJWT.requests;

// DrugRequest, ilaç oluşturma veya güncelleme işlemleri için kullanılan veri transfer nesnesidir (DTO).
public class DrugRequest {

    // İlaç adı alanı
    private String ilacAdi;

    // İlaç grubu alanı
    private String ilacGrubu;

    // İlaç etken maddesi alanı
    private String ilacEtkenMaddesi;

    // Getter ve setter metodları

    // İlaç adını döndüren getter metodu
    public String getIlacAdi() {
        return ilacAdi;
    }

    // İlaç adını ayarlayan setter metodu
    public void setIlacAdi(String ilacAdi) {
        this.ilacAdi = ilacAdi;
    }

    // İlaç grubunu döndüren getter metodu
    public String getIlacGrubu() {
        return ilacGrubu;
    }

    // İlaç grubunu ayarlayan setter metodu
    public void setIlacGrubu(String ilacGrubu) {
        this.ilacGrubu = ilacGrubu;
    }

    // İlaç etken maddesini döndüren getter metodu
    public String getIlacEtkenMaddesi() {
        return ilacEtkenMaddesi;
    }

    // İlaç etken maddesini ayarlayan setter metodu
    public void setIlacEtkenMaddesi(String ilacEtkenMaddesi) {
        this.ilacEtkenMaddesi = ilacEtkenMaddesi;
    }
}
