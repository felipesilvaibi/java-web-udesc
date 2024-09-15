package com.udesc.web.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.udesc.web.models.CustomerModel;
import com.udesc.web.repositories.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Object save(CustomerModel customerModel) {
        return customerRepository.save(customerModel);
    }

    public boolean existsByCpf(String cpf) {
        return customerRepository.existsByCpf(cpf);
    }

    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public boolean existsByPhone(String phone) {
        return customerRepository.existsByPhone(phone);
    }

    public List<CustomerModel> findAll() {
        return customerRepository.findAll();
    }

    public Optional<CustomerModel> findById(UUID id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public void delete(CustomerModel customerModel) {
        customerRepository.delete(customerModel);
    }
}
