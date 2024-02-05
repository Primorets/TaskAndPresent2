package com.example.taskandpresent2.purchase;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> getPurchasesByBuyer_Id(Long buyerId, Pageable pageable);

    @Query("SELECT p " +
            "FROM Purchase p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Purchase> searchPurchasesByName(@Param("search") String query, Pageable pageable);


}
