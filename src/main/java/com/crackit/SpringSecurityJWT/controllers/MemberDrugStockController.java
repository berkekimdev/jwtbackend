package com.crackit.SpringSecurityJWT.controllers;

import com.crackit.SpringSecurityJWT.requests.MemberDrugStockRequest;
import com.crackit.SpringSecurityJWT.responses.MemberDrugStockResponse;
import com.crackit.SpringSecurityJWT.services.MemberDrugStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController, bu sınıfın bir Spring MVC denetleyicisi olduğunu belirtir.
// Bu, HTTP isteklerini işlemek için kullanılır.
@RestController
@CrossOrigin
// @RequestMapping, bu denetleyicinin tüm yollarının "/api/drugstocks" ile başlaması gerektiğini belirtir.
@RequestMapping("/api/drugstocks")
public class MemberDrugStockController {

    // MemberDrugStockService, üye ilaç stokları ile ilgili işlemleri gerçekleştirmek için kullanılır.
    @Autowired
    private MemberDrugStockService memberDrugStockService;

    // Yeni bir ilaç stoğu ekleme endpoint'i
    @PostMapping
    public MemberDrugStockResponse addDrugStock(@RequestBody MemberDrugStockRequest request) {
        // İlaç stoğunu kaydetme işlemi
        return memberDrugStockService.saveDrugStock(request);
    }

    // Belirli bir ID'ye sahip ilaç stoğunu güncelleme endpoint'i
    @PutMapping("/{id}")
    public MemberDrugStockResponse updateDrugStock(@PathVariable Long id, @RequestBody MemberDrugStockRequest request) {
        // İlaç stoğunu güncelleme işlemi
        return memberDrugStockService.updateDrugStock(id, request);
    }

    // Tüm ilaç stoklarını getirme endpoint'i
    @GetMapping
    public List<MemberDrugStockResponse> getAllDrugStocks() {
        // Tüm ilaç stoklarını veritabanından bulma işlemi
        return memberDrugStockService.findAllDrugStocks();
    }
}
