package com.crackit.SpringSecurityJWT.services;


import com.crackit.SpringSecurityJWT.entities.MemberDrugStock;

import com.crackit.SpringSecurityJWT.entities.Drug;
import com.crackit.SpringSecurityJWT.repositories.MemberDrugStockRepository;

import com.crackit.SpringSecurityJWT.repositories.DrugRepository;
import com.crackit.SpringSecurityJWT.requests.MemberDrugStockRequest;
import com.crackit.SpringSecurityJWT.responses.MemberDrugStockResponse;
import com.crackit.SpringSecurityJWT.user.User;
import com.crackit.SpringSecurityJWT.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberDrugStockService {
    @Autowired
    private MemberDrugStockRepository memberDrugStockRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DrugRepository drugRepository;

    public MemberDrugStockResponse saveDrugStock(MemberDrugStockRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Drug drug = drugRepository.findById(request.getDrugId()).orElseThrow(() -> new RuntimeException("Drug not found"));

        MemberDrugStock drugStock = new MemberDrugStock();
        drugStock.setUser(user);
        drugStock.setDrug(drug);
        drugStock.setQuantity(request.getQuantity());

        MemberDrugStock savedDrugStock = memberDrugStockRepository.save(drugStock);
        return new MemberDrugStockResponse(savedDrugStock.getId(), savedDrugStock.getUser().getId(),
                savedDrugStock.getDrug().getId(), savedDrugStock.getQuantity());
    }

    public MemberDrugStockResponse updateDrugStock(Long id, MemberDrugStockRequest request) {
        MemberDrugStock drugStock = memberDrugStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug stock not found"));

        drugStock.setQuantity(request.getQuantity());  // Stoğun miktarını güncelle

        MemberDrugStock updatedDrugStock = memberDrugStockRepository.save(drugStock);
        return new MemberDrugStockResponse(updatedDrugStock.getId(),
                updatedDrugStock.getUser().getId(),
                updatedDrugStock.getDrug().getId(),
                updatedDrugStock.getQuantity());
    }
    public List<MemberDrugStockResponse> findAllDrugStocks() {
        List<MemberDrugStock> drugStocks = memberDrugStockRepository.findAll();
        return drugStocks.stream().map(drugStock -> new MemberDrugStockResponse(
                drugStock.getId(),
                drugStock.getUser().getId(),
                drugStock.getDrug().getId(),
                drugStock.getQuantity()
        )).collect(Collectors.toList());
    }


}
