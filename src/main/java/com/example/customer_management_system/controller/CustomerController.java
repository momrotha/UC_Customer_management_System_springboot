//package com.example.customer_management_system.controller;
//
//import com.example.customer_management_system.dto.CustomerRequest;
//import com.example.customer_management_system.entity.User;
//import com.example.customer_management_system.repository.UserRepository;
//import com.example.customer_management_system.service.CustomerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/customers")
//@RequiredArgsConstructor
//public class CustomerController {
//
//    private final CustomerService customerService;
//    private final UserRepository userRepository;
//
//
//    @GetMapping
//    public String list(
//            @RequestParam(defaultValue = "") String keyword,
//            Model model,
//            Authentication authentication
//    ) {
//        String email = authentication.getName();
//        User user = userRepository.findByEmail(email).orElseThrow();
//
//        model.addAttribute("customers",
//                customerService.findByUser(user, keyword));
//
//        return "customers/list";
//    }
//
//    @PostMapping
//    public String save(CustomerRequest request, Authentication authentication) {
//        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
//        customerService.save(request, user);
//        return "redirect:/customers";
//    }
//
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable Long id, Authentication authentication) {
//        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
//        customerService.delete(id, user);
//        return "redirect:/customers";
//    }
//
//    @PostMapping("/update/{id}")
//    public String update(@PathVariable Long id, CustomerRequest request, Authentication authentication) {
//        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
//        customerService.update(id, request, user);
//        return "redirect:/customers";
//    }
//}

package com.example.customer_management_system.controller;

import com.example.customer_management_system.dto.CustomerRequest;
import com.example.customer_management_system.entity.User;
import com.example.customer_management_system.repository.UserRepository;
import com.example.customer_management_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final UserRepository userRepository;

    @GetMapping
    public String list(
            @RequestParam(defaultValue = "") String keyword,
            Model model,
            Authentication authentication
    ) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        model.addAttribute("customers", customerService.findByUser(user, keyword));
        model.addAttribute("keyword", keyword);
        return "customers/list";
    }

//    @GetMapping("/new")
//    public String createForm(Model model) {
//        model.addAttribute("customer", new CustomerRequest("", "", ""));
//        return "customers/new";
//    }
    @GetMapping("/new")
    public String newCustomer() {
        return "customers/new"; // Thymeleaf will look for this template
    }


    @PostMapping
    public String save(CustomerRequest request, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        customerService.save(request, user);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        var customer = customerService.findById(id);
        if (!customer.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }
        model.addAttribute("customer", customer);
        return "customers/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, CustomerRequest request, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        customerService.update(id, request, user);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        customerService.delete(id, user);
        return "redirect:/customers";
    }
}

