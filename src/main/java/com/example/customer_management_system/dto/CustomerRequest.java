package com.example.customer_management_system.dto;

import lombok.Builder;

@Builder
public record CustomerRequest(
        String name,
        String email,
        String phone
) {}
