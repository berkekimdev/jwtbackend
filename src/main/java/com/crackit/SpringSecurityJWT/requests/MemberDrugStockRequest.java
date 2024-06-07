package com.crackit.SpringSecurityJWT.requests;

// MemberDrugStockRequest, üye ilaç stoğu işlemleri için kullanılan veri transfer nesnesidir (DTO).
public class MemberDrugStockRequest {

    // Kullanıcı ID'sini tutan alan
    private Integer userId;

    // İlaç ID'sini tutan alan
    private Long drugId;

    // İlaç miktarını tutan alan
    private int quantity;

    // Getter ve setter metodları

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
}
