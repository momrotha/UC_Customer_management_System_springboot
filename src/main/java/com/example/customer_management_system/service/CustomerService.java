package com.example.customer_management_system.service;

import com.example.customer_management_system.dto.CustomerRequest;
import com.example.customer_management_system.entity.Customer;
import com.example.customer_management_system.entity.User;

import java.util.List;

public interface CustomerService {

    List<Customer> findByUser(User user, String keyword);

    Customer findById(Long id);

    void save(CustomerRequest request, User user);

    void update(Long id, CustomerRequest request, User user);

    void delete(Long id, User user);
}
