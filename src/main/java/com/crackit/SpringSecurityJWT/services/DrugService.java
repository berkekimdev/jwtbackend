package com.crackit.SpringSecurityJWT.services;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.entities.MemberDrugStock;
import com.crackit.SpringSecurityJWT.repositories.DrugRepository;
import com.crackit.SpringSecurityJWT.repositories.MemberDrugStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private MemberDrugStockRepository memberDrugStockRepository;

    public Drug updateDrug(Long drugId, Drug drugDetails) {
        Drug drug = drugRepository.findById(drugId)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + drugId));

        drug.setIlacAdi(drugDetails.getIlacAdi());
        drug.setIlacGrubu(drugDetails.getIlacGrubu());
        drug.setIlacEtkenMaddesi(drugDetails.getIlacEtkenMaddesi());

        return drugRepository.save(drug);
    }

    public void deleteDrug(Long drugId) {
        drugRepository.deleteById(drugId);
    }

    public Drug saveDrug(Drug drug) {
        return drugRepository.save(drug);  // Veritabanına ilacı kaydeden metod
    }

    public List<Drug> findAllDrugs() {
        List<Drug> drugs = drugRepository.findAll();
        for (Drug drug : drugs) {
            int totalStock = memberDrugStockRepository.findByDrug(drug).stream()
                    .mapToInt(MemberDrugStock::getQuantity)
                    .sum();
            drug.setTotalStock(totalStock);
        }
        return drugs;
    }

    public Drug findDrugById(Long drugId) {
        return drugRepository.findById(drugId).orElse(null);
    }

    public List<Drug> searchDrugs(String searchTerm) {
        Drug drugProbe = new Drug();
        drugProbe.setIlacAdi(searchTerm);  // İlaç ismi
        drugProbe.setIlacGrubu(searchTerm);  // İlaç grubu

        ExampleMatcher caseInsensitiveMatcher = ExampleMatcher.matchingAny()
                .withMatcher("ilacAdi", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("ilacGrubu", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Drug> drugExample = Example.of(drugProbe, caseInsensitiveMatcher);
        List<Drug> results = drugRepository.findAll(drugExample);

        return results.isEmpty() ? List.of() : results;
    }

}
