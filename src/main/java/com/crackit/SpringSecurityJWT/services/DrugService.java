package com.crackit.SpringSecurityJWT.services;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.repositories.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    @Autowired
    private DrugRepository drugRepository;

    public Drug saveDrug(Drug drug) {
        return drugRepository.save(drug);  // Veritabanına ilacı kaydeden metod
    }

    public List<Drug> findAllDrugs() {
        return drugRepository.findAll();
    }
}
