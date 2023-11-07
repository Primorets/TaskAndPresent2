package com.example.taskandpresent2.purchase.model;

import com.example.taskandpresent2.purchase.Dimension;
import com.example.taskandpresent2.purchase.StatusPurchases;
import com.example.taskandpresent2.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseDto {
    private Long id;
    private String name;
    private StatusPurchases statusPurchases;
    private boolean IsConsumable;
    @JsonIgnore
    private User buyer;
    private Dimension dimension;
    private int quantity;
}
