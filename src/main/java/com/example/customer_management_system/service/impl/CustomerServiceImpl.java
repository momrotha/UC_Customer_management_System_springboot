package com.example.customer_management_system.service.impl;

import com.example.customer_management_system.dto.CustomerRequest;
import com.example.customer_management_system.entity.Customer;
import com.example.customer_management_system.repository.CustomerRepository;
import com.example.customer_management_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customerRepository.save(customer);
    }

    @Override
    public void update(Long id, CustomerRequest request) {
        Customer customer = findById(id);
        if (customer != null) {
            customer.setName(request.name());
            customer.setEmail(request.email());
            customer.setPhone(request.phone());
            customerRepository.save(customer);
        }
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    // ---------------- Pagination & Search ----------------

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> search(String keyword, Pageable pageable) {
        return customerRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, pageable);
    }
}
