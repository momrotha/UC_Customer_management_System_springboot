package com.example.customer_management_system.service.impl;

import com.example.customer_management_system.dto.CustomerRequest;
import com.example.customer_management_system.entity.Customer;
import com.example.customer_management_system.entity.User;
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
    public List<Customer> findByUser(User user, String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return customerRepository.findByUser(user);
        }

        return customerRepository
                .findByUserAndNameContainingIgnoreCaseOrUserAndEmailContainingIgnoreCase(
                        user, keyword, user, keyword, Pageable.unpaged()
                )
                .getContent();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(CustomerRequest request, User user) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setUser(user); // 🔐 IMPORTANT
        customerRepository.save(customer);
    }

    @Override
    public void update(Long id, CustomerRequest request, User user) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        if (!customer.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id, User user) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        if (!customer.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        customerRepository.delete(customer);
    }
}
