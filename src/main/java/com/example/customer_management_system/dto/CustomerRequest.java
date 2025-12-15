package com.example.customer_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CustomerRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50)
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Phone is required")
        @Size(min = 8, max = 15)
        String phone
) {}
