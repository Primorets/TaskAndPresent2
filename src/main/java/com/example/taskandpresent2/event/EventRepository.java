package com.example.taskandpresent2.event;

import com.example.taskandpresent2.purchase.Purchase;
import com.example.taskandpresent2.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EventRepository extends JpaRepository<Event,Long> {

     @Query("SELECT e FROM Event e JOIN e.participants u WHERE u.id = :buyer_id" )
     List<Event> findAllByParticipantId(@Param("buyer_id")Long buyerId, Pageable pageable);

     @Query("SELECT u FROM User u JOIN u.events e WHERE e.id = :event_id ")
     List<User> findAllUserByEventId (@Param("event_id") Long eventId, Pageable pageable);


     @Query("SELECT p FROM Event e JOIN e.purchases p WHERE e.id = :event_id")
     List<Purchase> findAllPurchasesByEventId(@Param("event_id")Long eventId, Pageable pageable);

}
