package com.crackit.SpringSecurityJWT.entities;

import com.crackit.SpringSecurityJWT.user.User;
import jakarta.persistence.*;

// @Entity, bu sınıfın bir JPA varlığı olduğunu belirtir. Bu sınıf, veritabanı tablosuna karşılık gelir.
@Entity

// @Table, bu varlığın karşılık geldiği veritabanı tablosunu belirtir.
// Bu durumda, "member_drug_stocks" adlı tabloya karşılık gelir.
@Table(name = "member_drug_stocks")
public class MemberDrugStock {

    // @Id, bu alanın birincil anahtar olduğunu belirtir.
    @Id

    // @GeneratedValue, birincil anahtarın nasıl üretileceğini belirtir.
    // GenerationType.IDENTITY, otomatik artan birincil anahtar kullanır.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne, bu alanın bir ilişki olduğunu belirtir.
    // Bu durumda, bir MemberDrugStock bir User'a ait olabilir.
    @ManyToOne

    // @JoinColumn, bu ilişkinin veritabanı sütununu belirtir.
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // @ManyToOne, bu alanın bir ilişki olduğunu belirtir.
    // Bu durumda, bir MemberDrugStock bir Drug'a ait olabilir.
    @ManyToOne

    // @JoinColumn, bu ilişkinin veritabanı sütununu belirtir.
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;

    // Kullanıcının sahip olduğu ilacın miktarını tutan alan.
    private int quantity;

    // Varsayılan yapıcı (constructor)
    public MemberDrugStock() {
        // Default constructor
    }

    // Parametreli yapıcı, kullanıcı, ilaç ve miktar ile yeni bir MemberDrugStock nesnesi oluşturur.
    public MemberDrugStock(User user, Drug drug, int quantity) {
        this.user = user;
        this.drug = drug;
        this.quantity = quantity;
    }

    // Getter ve setter metodları

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
