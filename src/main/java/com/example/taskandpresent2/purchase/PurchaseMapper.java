package com.example.taskandpresent2.purchase;

import com.example.taskandpresent2.event.EventMapper;
import com.example.taskandpresent2.event.EventService;
import com.example.taskandpresent2.purchase.model.PurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    @Autowired
    EventService eventService;

    public  Purchase toPurchase(PurchaseDto purchaseDto) {
        return new Purchase(purchaseDto.getId(),
                purchaseDto.getName(),
                purchaseDto.getStatusPurchases(),
                purchaseDto.isIsConsumable(),
                EventMapper.toEvent(eventService.getEventByIdForPurchase(purchaseDto.getEventDtoId())),
                purchaseDto.getBuyer(),
                purchaseDto.getDimension(),
                purchaseDto.getQuantity());
    }
    public static PurchaseDto toPurchaseDto(Purchase purchase){
        return new PurchaseDto(purchase.getId(),
                purchase.getName(),
                purchase.getStatusPurchases(),
                purchase.isIsConsumable(),
                purchase.getEvent().getId(),
                purchase.getBuyer(),
                purchase.getDimension(),
                purchase.getQuantity());
    }


}
