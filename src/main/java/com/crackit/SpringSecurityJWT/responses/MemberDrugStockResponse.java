package com.crackit.SpringSecurityJWT.responses;

// MemberDrugStockResponse, üye ilaç stoğu bilgilerini istemciye döndürmek için kullanılan veri transfer nesnesidir (DTO).
public class MemberDrugStockResponse {

    // İlaç stoğu ID'sini tutan alan
    private Long id;

    // Kullanıcı ID'sini tutan alan
    private Integer userId;

    // İlaç ID'sini tutan alan
    private Long drugId;

    // İlaç miktarını tutan alan
    private int quantity;

    // İlaç ismini tutan alan
    private String drugName;

    // Eczane adını (kullanıcı adını) tutan alan
    private String eczaneAdi;

    // Parametreli yapıcı (constructor), ilaç stoğu bilgilerini alarak yeni bir MemberDrugStockResponse nesnesi oluşturur.
    public MemberDrugStockResponse(Long id, Integer userId, Long drugId, int quantity, String drugName, String eczaneAdi) {
        this.id = id;
        this.userId = userId;
        this.drugId = drugId;
        this.quantity = quantity;
        this.drugName = drugName;
        this.eczaneAdi = eczaneAdi;
    }

    // Getter ve setter metodları

    // İlaç stoğu ID'sini döndüren getter metodu
    public Long getId() {
        return id;
    }

    // İlaç stoğu ID'sini ayarlayan setter metodu
    public void setId(Long id) {
        this.id = id;
    }

    // Kullanıcı ID'sini döndüren getter metodu
    public Integer getUserId() {
        return userId;
    }

    // Kullanıcı ID'sini ayarlayan setter metodu
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // İlaç ID'sini döndüren getter metodu
    public Long getDrugId() {
        return drugId;
    }

    // İlaç ID'sini ayarlayan setter metodu
    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    // İlaç miktarını döndüren getter metodu
    public int getQuantity() {
        return quantity;
    }

    // İlaç miktarını ayarlayan setter metodu
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // İlaç ismini döndüren getter metodu
    public String getDrugName() {
        return drugName;
    }

    // İlaç ismini ayarlayan setter metodu
    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    // Eczane adını döndüren getter metodu
    public String getEczaneAdi() {
        return eczaneAdi;
    }

    // Eczane adını ayarlayan setter metodu
    public void setEczaneAdi(String eczaneAdi) {
        this.eczaneAdi = eczaneAdi;
    }
}
