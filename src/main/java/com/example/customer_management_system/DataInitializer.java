package com.example.customer_management_system;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initUsers() {
        return args -> {
            // Admin user
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole("ROLE_ADMIN"); // Must start with ROLE_
                userRepository.save(admin);
            }

            // Employee user
            if (userRepository.findByEmail("employee@gmail.com").isEmpty()) {
                User emp = new User();
                emp.setEmail("employee@gmail.com");
                emp.setPassword(passwordEncoder.encode("1234"));
                emp.setRole("ROLE_EMPLOYEE");
                userRepository.save(emp);
            }
        };
    }
}

