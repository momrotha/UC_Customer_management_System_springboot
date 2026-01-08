package com.example.customer_management_system;

import java.util.List;

public interface CustomerService {

    List<Customer> findByUser(User user, String keyword);

    Customer findById(Long id);

    void save(CustomerRequest request, User user);

    // Pass user to update/delete for role-based access
    void update(Long id, CustomerRequest request, User user);

    void delete(Long id, User user);
}

