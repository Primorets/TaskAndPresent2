package com.example.taskandpresent2.purchase;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> getPurchasesByBuyer_Id(Long buyerId, Pageable pageable);

}
