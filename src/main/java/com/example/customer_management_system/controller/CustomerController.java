package com.example.customer_management_system.controller;

import com.example.customer_management_system.dto.CustomerRequest;
import com.example.customer_management_system.entity.Customer;
import com.example.customer_management_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers/list";
    }

    // SHOW CREATE FORM
    @GetMapping("/new")
    public String createForm() {
        return "customers/create";
    }

    // SAVE (manual mapping to record)
    @PostMapping
    public String save(HttpServletRequest request) {
        CustomerRequest customerRequest = CustomerRequest.builder()
                .name(request.getParameter("name"))
                .email(request.getParameter("email"))
                .phone(request.getParameter("phone"))
                .build();

        customerService.save(customerRequest);
        return "redirect:/customers";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customers/edit";
    }

    // UPDATE
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, HttpServletRequest request) {
        CustomerRequest customerRequest = CustomerRequest.builder()
                .name(request.getParameter("name"))
                .email(request.getParameter("email"))
                .phone(request.getParameter("phone"))
                .build();

        customerService.update(id, customerRequest);
        return "redirect:/customers";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}
