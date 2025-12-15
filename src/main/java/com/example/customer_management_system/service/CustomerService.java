package com.example.customer_management_system.service;

import com.example.customer_management_system.dto.CustomerRequest;
import com.example.customer_management_system.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(CustomerRequest request);
    void update(Long id, CustomerRequest request);
    void delete(Long id);

    // Pagination & Search
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> search(String keyword, Pageable pageable);
}
