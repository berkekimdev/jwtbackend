package com.crackit.SpringSecurityJWT.requests;

public class MemberDrugStockRequest {
    private  Integer userId;
    private Long drugId;
    private int quantity;

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId( Integer userId) {
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
}
