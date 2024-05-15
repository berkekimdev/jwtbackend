package com.crackit.SpringSecurityJWT.repositories;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.entities.MemberDrugStock;
import com.crackit.SpringSecurityJWT.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberDrugStockRepository extends JpaRepository<MemberDrugStock, Long> {

    Optional<MemberDrugStock> findByUserAndDrug(User user, Drug drug);

    List<MemberDrugStock> findByDrug(Drug drug);

    List<MemberDrugStock> findByDrugId(Long drugId);


}
