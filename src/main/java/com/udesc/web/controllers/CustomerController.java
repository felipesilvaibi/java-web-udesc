package com.udesc.web.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerModel));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerModel>> getCustomers(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable(value = "id") UUID id) {
        Optional<CustomerModel> customerModel = customerService.findById(id);

        if (!customerModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable(value = "id") UUID id) {
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);

        if (!customerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }

        customerService.delete(customerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomerById(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid CustomerDto customerDto) {
        Optional<CustomerModel> customerModelOptional = customerService.findById(id);

        if (!customerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }

        var customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerDto, customerModel);
        customerModel.setId(id);
        customerModel.setCreatedAt(customerModelOptional.get().getCreatedAt());

        return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customerModel));
    }

}
