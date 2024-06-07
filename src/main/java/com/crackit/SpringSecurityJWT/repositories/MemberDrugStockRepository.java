package com.crackit.SpringSecurityJWT.repositories;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.entities.MemberDrugStock;
import com.crackit.SpringSecurityJWT.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// MemberDrugStockRepository, JPA repository aracılığıyla MemberDrugStock varlıkları üzerinde CRUD işlemleri yapar.
public interface MemberDrugStockRepository extends JpaRepository<MemberDrugStock, Long> {

    // Kullanıcı ve ilaca göre ilaç stoğunu bulma yöntemi
    Optional<MemberDrugStock> findByUserAndDrug(User user, Drug drug);

    // Belirli bir ilaca ait tüm ilaç stoklarını bulma yöntemi
    List<MemberDrugStock> findByDrug(Drug drug);

    // Belirli bir ilaç ID'sine ait tüm ilaç stoklarını bulma yöntemi
    List<MemberDrugStock> findByDrugId(Long drugId);
}
