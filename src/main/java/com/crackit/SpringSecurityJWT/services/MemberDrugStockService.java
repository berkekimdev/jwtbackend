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

// @Service, bu sınıfın bir servis bileşeni olduğunu belirtir ve Spring tarafından yönetilir.
@Service
public class MemberDrugStockService {

    // MemberDrugStockRepository, üye ilaç stoğu veritabanı işlemleri için kullanılır.
    @Autowired
    private MemberDrugStockRepository memberDrugStockRepository;

    // UserRepository, kullanıcı veritabanı işlemleri için kullanılır.
    @Autowired
    private UserRepository userRepository;

    // DrugRepository, ilaç veritabanı işlemleri için kullanılır.
    @Autowired
    private DrugRepository drugRepository;

    // Üye ilaç stoğu kaydetme işlemi
    public MemberDrugStockResponse saveDrugStock(MemberDrugStockRequest request) {
        // Kullanıcıyı ve ilacı bul
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        Drug drug = drugRepository.findById(request.getDrugId()).orElseThrow(() -> new RuntimeException("İlaç bulunamadı"));

        // Mevcut stoğu kontrol et veya yeni bir stok oluştur
        Optional<MemberDrugStock> existingStock = memberDrugStockRepository.findByUserAndDrug(user, drug);
        MemberDrugStock drugStock = existingStock.orElseGet(MemberDrugStock::new);
        drugStock.setUser(user);
        drugStock.setDrug(drug);
        drugStock.setQuantity(request.getQuantity());

        // İlaç stoğunu kaydet ve yanıt olarak dön
        MemberDrugStock savedDrugStock = memberDrugStockRepository.save(drugStock);
        return new MemberDrugStockResponse(
                savedDrugStock.getId(),
                savedDrugStock.getUser().getId(),
                savedDrugStock.getDrug().getId(),
                savedDrugStock.getQuantity(),
                savedDrugStock.getDrug().getIlacAdi(),
                savedDrugStock.getUser().getEczaneAdi()
        );
    }

    // Üye ilaç stoğunu güncelleme işlemi
    public MemberDrugStockResponse updateDrugStock(Long id, MemberDrugStockRequest request) {
        // Belirtilen ID'ye sahip ilaç stoğunu bul
        MemberDrugStock drugStock = memberDrugStockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug stock not found"));

        // Stoğun miktarını güncelle
        drugStock.setQuantity(request.getQuantity());

        // Güncellenen stoğu kaydet ve yanıt olarak dön
        MemberDrugStock updatedDrugStock = memberDrugStockRepository.save(drugStock);
        return new MemberDrugStockResponse(
                updatedDrugStock.getId(),
                updatedDrugStock.getUser().getId(),
                updatedDrugStock.getDrug().getId(),
                updatedDrugStock.getQuantity(),
                updatedDrugStock.getDrug().getIlacAdi(),
                updatedDrugStock.getUser().getEczaneAdi()
        );
    }

    // Tüm üye ilaç stoklarını bulma işlemi
    public List<MemberDrugStockResponse> findAllDrugStocks() {
        List<MemberDrugStock> stocks = memberDrugStockRepository.findAll();
        return stocks.stream().map(stock -> new MemberDrugStockResponse(
                stock.getId(),
                stock.getUser().getId(),
                stock.getDrug().getId(),
                stock.getQuantity(),
                stock.getDrug().getIlacAdi(),
                stock.getUser().getEczaneAdi()
        )).collect(Collectors.toList());
    }
}
