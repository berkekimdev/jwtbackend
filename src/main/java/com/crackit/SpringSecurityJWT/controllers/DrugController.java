package com.crackit.SpringSecurityJWT.controllers;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> searchDrugs(@RequestParam String query) {
        List<Drug> drugs = drugService.searchDrugs(query);
        if (drugs.isEmpty()) {
            return ResponseEntity.ok("Böyle bir ilaç veya ilaç grubu bulunamadı.");
        } else {
            return ResponseEntity.ok(drugs);
        }
    }


}
