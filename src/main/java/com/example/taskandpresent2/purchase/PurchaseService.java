package com.example.taskandpresent2.purchase;

import com.example.taskandpresent2.purchase.model.PurchaseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PurchaseService {
    PurchaseDto getPurchaseById(Long id);

    List<PurchaseDto> getAllPurchases();

    List<PurchaseDto> getAllPurchasesByBuyerId(Long buyerId, int from, int size);

    PurchaseDto createPurchase(Long userId, PurchaseDto PurchaseDto);

    PurchaseDto updatePurchase(Long userId,PurchaseDto user, Long id);


    @Transactional
    void deletePurchaseById(Long userId, Long id);

    List<PurchaseDto> searchAllPurchases(String text, int from, int size);
}
