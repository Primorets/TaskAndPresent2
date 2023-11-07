package com.example.taskandpresent2.purchase;

import com.example.taskandpresent2.purchase.model.PurchaseDto;

import java.util.List;

public interface PurchaseService {
    PurchaseDto getPurchaseById(Long id);

    List<PurchaseDto> getAllPurchases();

    List<PurchaseDto> getAllPurchasesByBuyerId(Long buyerId, int from, int size);

    PurchaseDto createPurchase(PurchaseDto PurchaseDto);

    PurchaseDto updatePurchase(PurchaseDto user, Long id);

    void deletePurchaseById(Long id);
}
