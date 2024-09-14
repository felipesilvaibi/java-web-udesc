package com.udesc.web.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udesc.web.models.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}