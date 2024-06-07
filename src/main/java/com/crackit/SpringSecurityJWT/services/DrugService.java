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

// @Service, bu sınıfın bir servis bileşeni olduğunu belirtir ve Spring tarafından yönetilir.
@Service
public class DrugService {

    // DrugRepository, ilaç veritabanı işlemleri için kullanılır.
    @Autowired
    private DrugRepository drugRepository;

    // MemberDrugStockRepository, üye ilaç stoğu veritabanı işlemleri için kullanılır.
    @Autowired
    private MemberDrugStockRepository memberDrugStockRepository;

    // İlaç güncelleme işlemi
    public Drug updateDrug(Long drugId, Drug drugDetails) {
        // Belirtilen ID'ye sahip ilacı bul
        Drug drug = drugRepository.findById(drugId)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + drugId));

        // İlaç detaylarını güncelle
        drug.setIlacAdi(drugDetails.getIlacAdi());
        drug.setIlacGrubu(drugDetails.getIlacGrubu());
        drug.setIlacEtkenMaddesi(drugDetails.getIlacEtkenMaddesi());

        // Güncellenen ilacı kaydet
        return drugRepository.save(drug);
    }

    // İlaç silme işlemi
    public void deleteDrug(Long drugId) {
        // İlaçla ilgili tüm stokları sil
        List<MemberDrugStock> stocks = memberDrugStockRepository.findByDrugId(drugId);
        for (MemberDrugStock stock : stocks) {
            memberDrugStockRepository.delete(stock);
        }
        // İlaç kaydını sil
        drugRepository.deleteById(drugId);
    }

    // İlaç ismine göre ilaçları bulma işlemi
    public List<Drug> findDrugsByName(String name) {
        return drugRepository.findByIlacAdiContainingIgnoreCase(name);
    }

    // İlaç kaydetme işlemi
    public Drug saveDrug(Drug drug) {
        return drugRepository.save(drug); // Veritabanına ilacı kaydeder
    }

    // Tüm ilaçları bulma işlemi
    public List<Drug> findAllDrugs() {
        List<Drug> drugs = drugRepository.findAll();
        for (Drug drug : drugs) {
            // İlaç stok miktarını hesapla
            int totalStock = memberDrugStockRepository.findByDrug(drug).stream()
                    .mapToInt(MemberDrugStock::getQuantity)
                    .sum();
            drug.setTotalStock(totalStock);
        }
        return drugs;
    }

    // Belirli bir ID'ye sahip ilacı bulma işlemi
    public Drug findDrugById(Long drugId) {
        return drugRepository.findById(drugId).orElse(null);
    }

    // İlaç arama işlemi
    public List<Drug> searchDrugs(String searchTerm) {
        Drug drugProbe = new Drug();
        drugProbe.setIlacAdi(searchTerm); // İlaç ismi
        drugProbe.setIlacGrubu(searchTerm); // İlaç grubu
        drugProbe.setIlacEtkenMaddesi(searchTerm);

        // Arama eşleştiricisini tanımla
        ExampleMatcher caseInsensitiveMatcher = ExampleMatcher.matchingAny()
                .withMatcher("ilacAdi", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("ilacGrubu", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("ilacEtkenMaddesi", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Drug> drugExample = Example.of(drugProbe, caseInsensitiveMatcher);
        List<Drug> results = drugRepository.findAll(drugExample);

        return results.isEmpty() ? List.of() : results;
    }

    // İlaç grubuna göre ilaçları bulma işlemi
    public List<Drug> findDrugsByGroup(String group) {
        return drugRepository.findByIlacGrubu(group);
    }

    // Belirli bir harfle başlayan ilaçları bulma işlemi
    public List<Drug> findDrugsByFirstLetter(String letter) {
        return drugRepository.findByIlacAdiStartingWithIgnoreCase(letter);
    }

    // En çok aranan ilaçları bulma işlemi
    public List<Drug> findTopSearchedDrugs() {
        return drugRepository.findTop10ByOrderBySearchCountDesc();
    }

    // En son eklenen ilaçları bulma işlemi
    public List<Drug> findLatestDrugs() {
        return drugRepository.findTop10ByOrderByCreatedAtAsc(); // En eski eklenen ilaçları getir
    }

    // Etken maddeye göre ilaçları bulma işlemi
    public List<Drug> findDrugsByActiveIngredient(String activeIngredient) {
        return drugRepository.findByIlacEtkenMaddesiContainingIgnoreCase(activeIngredient);
    }
}
