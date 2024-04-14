package com.crackit.SpringSecurityJWT.responses;

public class MemberDrugStockResponse {
    private Long id;
    private  Integer userId;
    private Long drugId;
    private int quantity;

    // Constructors, Getters and Setters
    public MemberDrugStockResponse(Long id,  Integer userId, Long drugId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.drugId = drugId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  Integer getUserId() {
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
