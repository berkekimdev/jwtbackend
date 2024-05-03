package com.crackit.SpringSecurityJWT.responses;

public class MemberDrugStockResponse {
    private Long id;
    private Integer userId;
    private Long drugId;
    private int quantity;
    private String drugName;  // İlaç ismi
    private String eczaneAdi;  // Kullanıcı adı

    // Constructor, getters and setters
    public MemberDrugStockResponse(Long id, Integer userId, Long drugId, int quantity, String drugName, String eczaneAdi) {
        this.id = id;
        this.userId = userId;
        this.drugId = drugId;
        this.quantity = quantity;
        this.drugName = drugName;
        this.eczaneAdi = eczaneAdi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getEczaneAdi() {
        return eczaneAdi;
    }

    public void setEczaneAdi(String eczaneAdi) {
        this.eczaneAdi = eczaneAdi;
    }
}
