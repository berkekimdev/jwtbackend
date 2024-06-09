package com.crackit.SpringSecurityJWT.controllers;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

// @RestController, bu sınıfın bir Spring MVC denetleyicisi olduğunu belirtir.
// Bu, HTTP isteklerini işlemek için kullanılır.
@RestController
@CrossOrigin

// @RequestMapping, bu denetleyicinin tüm yollarının "/api/drugs" ile başlaması gerektiğini belirtir.
@RequestMapping("/api/drugs")
public class DrugController {

    // DrugService, ilaç veritabanı işlemleri için kullanılır.
    @Autowired
    private DrugService drugService;

    // Yeni bir ilaç ekleme endpoint'i
    @PostMapping
    public Drug addDrug(@RequestBody Drug drug) {
        // İlaç kaydetme işlemi
        return drugService.saveDrug(drug);
    }

    // Tüm ilaçları getirme endpoint'i
    @GetMapping
    public List<Drug> getAllDrugs() {
        // Tüm ilaçları veritabanından bulma işlemi
        return drugService.findAllDrugs();
    }

    // Belirli bir gruba ait ilaçları getirme endpoint'i
    @GetMapping("/byGroup")
    public ResponseEntity<List<Drug>> getDrugsByGroup(@RequestParam String group) {
        // İlaçları gruba göre veritabanından bulma işlemi
        List<Drug> drugs = drugService.findDrugsByGroup(group);
        // Eğer ilaçlar bulunamazsa veya boşsa, 404 Not Found yanıtı döndür
        if (drugs == null || drugs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // İlaçlar bulunursa, 200 OK yanıtı döndür
        return ResponseEntity.ok(drugs);
    }

    // Belirli bir ID'ye sahip ilacı getirme endpoint'i
    @GetMapping("/{drugId}")
    public ResponseEntity<Drug> getDrugById(@PathVariable Long drugId) {
        // İlaçları ID'ye göre veritabanından bulma işlemi
        Drug drug = drugService.findDrugById(drugId);
        // Eğer ilaç bulunursa, 200 OK yanıtı döndür
        if (drug != null) {
            return ResponseEntity.ok(drug);
        } else {
            // Eğer ilaç bulunamazsa, 404 Not Found yanıtı döndür
            return ResponseEntity.notFound().build();
        }
    }

    // Belirli bir ID'ye sahip ilacı silme endpoint'i
    @DeleteMapping("/{drugId}")
    public ResponseEntity<?> deleteDrug(@PathVariable Long drugId) {
        // İlaç silme işlemi
        drugService.deleteDrug(drugId);
        // Başarılı bir şekilde silindiğinde 200 OK yanıtı döndür
        return ResponseEntity.ok("Drug deleted successfully");
    }

    // Belirli bir ID'ye sahip ilacı güncelleme endpoint'i
    @PutMapping("/{drugId}")
    public ResponseEntity<Drug> updateDrug(@PathVariable Long drugId, @RequestBody Drug drugDetails) {
        // İlaç güncelleme işlemi
        Drug updatedDrug = drugService.updateDrug(drugId, drugDetails);
        // Güncellenmiş ilacı 200 OK yanıtı ile döndür
        return ResponseEntity.ok(updatedDrug);
    }

    // İlaç arama endpoint'i
    @GetMapping("/search")
    public ResponseEntity<?> searchDrugs(@RequestParam String query, @RequestParam String type) {
        List<Drug> drugs;
        // Arama tipine göre ilaçları bulma işlemi
        if (type.equals("ilacGrubu")) {
            drugs = drugService.findDrugsByGroup(query);
        } else if (type.equals("etkenMadde")) {
            drugs = drugService.findDrugsByActiveIngredient(query);
        } else {
            drugs = drugService.findDrugsByName(query);
            // Bulunan ilaçların arama sayacını artırma işlemi
            if (!drugs.isEmpty()) {
                drugs.forEach(drug -> {
                    drug.setSearchCount(drug.getSearchCount() + 1);
                    drugService.saveDrug(drug);
                });
            }
        }
        // Eğer ilaçlar bulunamazsa, 200 OK yanıtı ile sonuç bulunamadı mesajı döndür
        if (drugs.isEmpty()) {
            return ResponseEntity.ok("Sonuç bulunamadı.");
        } else {
            // Eğer ilaçlar bulunursa, 200 OK yanıtı ile ilaçları döndür
            return ResponseEntity.ok(drugs);
        }
    }

    // Belirli bir harfle başlayan ilaçları getirme endpoint'i
    @GetMapping("/byFirstLetter")
    public ResponseEntity<List<Drug>> getDrugsByFirstLetter(@RequestParam String letter) {
        // İlaçları ilk harfe göre veritabanından bulma işlemi
        List<Drug> drugs = drugService.findDrugsByFirstLetter(letter);
        // Eğer ilaçlar bulunamazsa veya boşsa, 404 Not Found yanıtı döndür
        if (drugs == null || drugs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // İlaçlar bulunursa, 200 OK yanıtı döndür
        return ResponseEntity.ok(drugs);
    }

    // En çok aranan ilaçları getirme endpoint'i
    @GetMapping("/top-searched")
    public ResponseEntity<List<Drug>> getTopSearchedDrugs() {
        // En çok aranan ilaçları veritabanından bulma işlemi
        List<Drug> drugs = drugService.findTopSearchedDrugs();
        // 200 OK yanıtı ile ilaçları döndür
        return ResponseEntity.ok(drugs);
    }

    // En son eklenen ilaçları getirme endpoint'i
    @GetMapping("/latest")
    public List<Drug> getLatestDrugs() {
        // En son eklenen ilaçları veritabanından bulma işlemi
        return drugService.findLatestDrugs();
    }
}
