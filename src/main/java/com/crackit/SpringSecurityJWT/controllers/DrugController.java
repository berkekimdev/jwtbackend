package com.crackit.SpringSecurityJWT.controllers;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
