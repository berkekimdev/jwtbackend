package com.crackit.SpringSecurityJWT.responses;

// DrugResponse, ilaç bilgilerini istemciye döndürmek için kullanılan veri transfer nesnesidir (DTO).
public class DrugResponse {

    // İlaç ID'sini tutan alan
    private Long id;

    // İlaç adını tutan alan
    private String ilacAdi;

    // İlaç grubunu tutan alan
    private String ilacGrubu;

    // İlaç etken maddesini tutan alan
    private String ilacEtkenMaddesi;

    // Parametreli yapıcı (constructor), ilaç bilgilerini alarak yeni bir DrugResponse nesnesi oluşturur.
    public DrugResponse(Long id, String ilacAdi, String ilacGrubu, String ilacEtkenMaddesi) {
        this.id = id;
        this.ilacAdi = ilacAdi;
        this.ilacGrubu = ilacGrubu;
        this.ilacEtkenMaddesi = ilacEtkenMaddesi;
    }

    // Getter ve setter metodları

    // İlaç ID'sini döndüren getter metodu
    public Long getId() {
        return id;
    }

    // İlaç ID'sini ayarlayan setter metodu
    public void setId(Long id) {
        this.id = id;
    }

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
