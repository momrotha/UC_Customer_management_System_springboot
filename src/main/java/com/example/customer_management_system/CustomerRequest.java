package com.example.customer_management_system;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerRequest {

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50)
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String email;

        @NotBlank(message = "Phone is required")
        @Size(min = 8, max = 15)
        private String phone;

        // Default constructor
        public CustomerRequest() {
        }

        // All-args constructor
        public CustomerRequest(String name, String email, String phone) {
                this.name = name;
                this.email = email;
                this.phone = phone;
        }

        // Getter and Setter for name
        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        // Getter and Setter for email
        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        // Getter and Setter for phone
        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }
}
