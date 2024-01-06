package com.example.taskandpresent2.event;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
     List<Event> findAllByParticipantId(Long buyerId, Pageable pageable);

}
