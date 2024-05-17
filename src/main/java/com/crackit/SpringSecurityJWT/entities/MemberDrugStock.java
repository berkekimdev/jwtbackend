package com.crackit.SpringSecurityJWT.entities;

import com.crackit.SpringSecurityJWT.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "member_drug_stocks")
public class MemberDrugStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;

    private int quantity;

    public MemberDrugStock() {
        // Default constructor
    }

    public MemberDrugStock(User user, Drug drug, int quantity) {
        this.user = user;
        this.drug = drug;
        this.quantity = quantity;
    }

    // Getter and setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
