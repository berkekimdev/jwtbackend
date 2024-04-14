package com.crackit.SpringSecurityJWT.repositories;

import com.crackit.SpringSecurityJWT.entities.MemberDrugStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDrugStockRepository extends JpaRepository<MemberDrugStock, Long> {
}
