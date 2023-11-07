package com.example.taskandpresent2.purchase;

import com.example.taskandpresent2.purchase.model.PurchaseDto;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {
    public static Purchase toPurchase(PurchaseDto purchaseDto) {
        return new Purchase(purchaseDto.getId(),
                purchaseDto.getPurchases(),
                purchaseDto.getStatusPurchases(),
                purchaseDto.isIsConsumable(),
                purchaseDto.getBuyer(),
                purchaseDto.getDimension(),
                purchaseDto.getQuantity());
    }
    public static PurchaseDto toPurchaseDto(Purchase purchase){
        return new PurchaseDto(purchase.getId(),
                purchase.getPurchases(),
                purchase.getStatusPurchases(),
                purchase.isIsConsumable(),
                purchase.getBuyer(),
                purchase.getDimension(),
                purchase.getQuantity());
    }


}
