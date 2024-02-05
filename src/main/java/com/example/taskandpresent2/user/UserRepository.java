package com.example.taskandpresent2.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u " +
            "FROM User u " +
            "WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%'))" +
            " OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<User> searchUserByName(@Param("search") String query, Pageable pageable);



}
