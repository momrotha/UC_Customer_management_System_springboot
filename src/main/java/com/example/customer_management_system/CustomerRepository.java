package com.example.customer_management_system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByUser(User user);

    Page<Customer> findByUserAndNameContainingIgnoreCaseOrUserAndEmailContainingIgnoreCase(
            User user1, String name,
            User user2, String email,
            Pageable pageable
    );
}



