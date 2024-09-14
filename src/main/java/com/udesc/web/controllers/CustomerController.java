package com.udesc.web.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udesc.web.dtos.CustomerDto;
import com.udesc.web.models.CustomerModel;
import com.udesc.web.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/customer")
public class CustomerController {

    final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        if (customerService.existsByCpf(customerDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF already exists");
        }

        if (customerService.existsByEmail(customerDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        if (customerService.existsByPhone(customerDto.getPhone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone already exists");
        }

        var customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerDto, customerModel);
        customerModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customerModel));
    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

}
