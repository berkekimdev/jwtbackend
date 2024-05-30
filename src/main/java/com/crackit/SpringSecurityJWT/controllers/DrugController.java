package com.crackit.SpringSecurityJWT.controllers;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/drugs")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @PostMapping
    public Drug addDrug(@RequestBody Drug drug) {
        return drugService.saveDrug(drug);
    }

    @GetMapping
    public List<Drug> getAllDrugs() {
        return drugService.findAllDrugs();
    }

    @GetMapping("/byGroup")
    public ResponseEntity<List<Drug>> getDrugsByGroup(@RequestParam String group) {
        List<Drug> drugs = drugService.findDrugsByGroup(group);
        if (drugs == null || drugs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(drugs);
    }

    @GetMapping("/{drugId}")
    public ResponseEntity<Drug> getDrugById(@PathVariable Long drugId) {
        Drug drug = drugService.findDrugById(drugId);
        if (drug != null) {
            return ResponseEntity.ok(drug);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{drugId}")
    public ResponseEntity<?> deleteDrug(@PathVariable Long drugId) {
        drugService.deleteDrug(drugId);
        return ResponseEntity.ok("Drug deleted successfully");
    }

    @PutMapping("/{drugId}")
    public ResponseEntity<Drug> updateDrug(@PathVariable Long drugId, @RequestBody Drug drugDetails) {
        Drug updatedDrug = drugService.updateDrug(drugId, drugDetails);
        return ResponseEntity.ok(updatedDrug);
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchDrugs(@RequestParam String query, @RequestParam String type) {
        List<Drug> drugs;
        if (type.equals("ilacGrubu")) {
            drugs = drugService.findDrugsByGroup(query);
        } else if (type.equals("etkenMadde")) {
            drugs = drugService.findDrugsByActiveIngredient(query);
        } else {
            drugs = drugService.findDrugsByName(query);
            if (!drugs.isEmpty()) {
                drugs.forEach(drug -> {
                    drug.setSearchCount(drug.getSearchCount() + 1);
                    drugService.saveDrug(drug);
                });
            }
        }
        if (drugs.isEmpty()) {
            return ResponseEntity.ok("Sonuç bulunamadı.");
        } else {
            return ResponseEntity.ok(drugs);
        }
    }



    @GetMapping("/byFirstLetter")
    public ResponseEntity<List<Drug>> getDrugsByFirstLetter(@RequestParam String letter) {
        List<Drug> drugs = drugService.findDrugsByFirstLetter(letter);
        if (drugs == null || drugs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(drugs);
    }

    @GetMapping("/top-searched")
    public ResponseEntity<List<Drug>> getTopSearchedDrugs() {
        List<Drug> drugs = drugService.findTopSearchedDrugs();
        return ResponseEntity.ok(drugs);
    }

    @GetMapping("/latest")
    public List<Drug> getLatestDrugs() {
        return drugService.findLatestDrugs(); // En son eklenen ilaçları getir
    }
}
