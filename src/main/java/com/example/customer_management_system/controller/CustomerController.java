package com.example.customer_management_system.controller;

import com.example.customer_management_system.dto.CustomerRequest;
import com.example.customer_management_system.entity.Customer;
import com.example.customer_management_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("customers")  // Note: use /customers instead of /api/v1/customers for Thymeleaf UI
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // ---------------- LIST WITH PAGINATION + SEARCH ----------------
    @GetMapping
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword,
            Model model) {

        Page<Customer> customers;
        if (keyword.isEmpty()) {
            customers = customerService.findAll(PageRequest.of(page, size));
        } else {
            customers = customerService.search(keyword, PageRequest.of(page, size));
        }

        model.addAttribute("customers", customers.getContent());
        model.addAttribute("totalPages", customers.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "customers/list";
    }

    // ---------------- CREATE FORM ----------------
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("customer", new CustomerRequest(null, null, null));
        return "customers/create";
    }

    // ---------------- SAVE ----------------
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

    // ---------------- EDIT FORM ----------------
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customers/edit";
    }

    // ---------------- UPDATE ----------------
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

    // ---------------- DELETE ----------------
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}
