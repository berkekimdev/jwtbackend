package com.crackit.SpringSecurityJWT.controllers;

import com.crackit.SpringSecurityJWT.requests.MemberDrugStockRequest;
import com.crackit.SpringSecurityJWT.responses.MemberDrugStockResponse;
import com.crackit.SpringSecurityJWT.services.MemberDrugStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugstocks")
public class MemberDrugStockController {
    @Autowired
    private MemberDrugStockService memberDrugStockService;

    @PostMapping
    public MemberDrugStockResponse addDrugStock(@RequestBody MemberDrugStockRequest request) {
        return memberDrugStockService.saveDrugStock(request);
    }

    @PutMapping("/{id}")
    public MemberDrugStockResponse updateDrugStock(@PathVariable Long id, @RequestBody MemberDrugStockRequest request) {
        return memberDrugStockService.updateDrugStock(id, request);
    }

    @GetMapping
    public List<MemberDrugStockResponse> getAllDrugStocks() {
        return memberDrugStockService.findAllDrugStocks();
    }


}
