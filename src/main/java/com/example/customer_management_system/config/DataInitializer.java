package com.example.customer_management_system.config;

import com.example.customer_management_system.entity.User;
import com.example.customer_management_system.repository.UserRepository;
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
    CommandLineRunner initAdmin() {
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                User u = new User();
                u.setEmail("admin@gmail.com");
                u.setPassword(passwordEncoder.encode("1234"));
                u.setRole("USER");
                userRepository.save(u);
            }
        };
    }
}
