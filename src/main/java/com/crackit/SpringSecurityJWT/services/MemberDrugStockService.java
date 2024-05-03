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
import java.util.Optional;
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
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        Drug drug = drugRepository.findById(request.getDrugId()).orElseThrow(() -> new RuntimeException("İlaç bulunamadı"));

        Optional<MemberDrugStock> existingStock = memberDrugStockRepository.findByUserAndDrug(user, drug);
        MemberDrugStock drugStock = existingStock.orElseGet(MemberDrugStock::new);
        drugStock.setUser(user);
        drugStock.setDrug(drug);
        drugStock.setQuantity(request.getQuantity());

        MemberDrugStock savedDrugStock = memberDrugStockRepository.save(drugStock);
        return new MemberDrugStockResponse(
                savedDrugStock.getId(),
                savedDrugStock.getUser().getId(),
                savedDrugStock.getDrug().getId(),
                savedDrugStock.getQuantity(),
                savedDrugStock.getDrug().getIlacAdi(),
                savedDrugStock.getUser().getFirstName()
        );
    }


    public MemberDrugStockResponse updateDrugStock(Long id, MemberDrugStockRequest request) {
        MemberDrugStock drugStock = memberDrugStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug stock not found"));

        drugStock.setQuantity(request.getQuantity());  // Stoğun miktarını güncelle

        MemberDrugStock updatedDrugStock = memberDrugStockRepository.save(drugStock);
        return new MemberDrugStockResponse(
                updatedDrugStock.getId(),
                updatedDrugStock.getUser().getId(),
                updatedDrugStock.getDrug().getId(),
                updatedDrugStock.getQuantity(),
                updatedDrugStock.getDrug().getIlacAdi(),  // İlaç ismi
                updatedDrugStock.getUser().getFirstName()  // Kullanıcı adı
        );
    }

    public List<MemberDrugStockResponse> findAllDrugStocks() {
        List<MemberDrugStock> stocks = memberDrugStockRepository.findAll();
        return stocks.stream().map(stock -> new MemberDrugStockResponse(
                stock.getId(),
                stock.getUser().getId(),
                stock.getDrug().getId(),
                stock.getQuantity(),
                stock.getDrug().getIlacAdi(),  // İlaç ismi
                stock.getUser().getFirstName()  // Kullanıcı adı (eczane veya bireysel kullanıcı adı)
        )).collect(Collectors.toList());
    }





}
