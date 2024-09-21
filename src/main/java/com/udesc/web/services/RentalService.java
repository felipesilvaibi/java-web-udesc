package com.udesc.web.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.udesc.web.models.RentalModel;
import com.udesc.web.repositories.RentalRepository;

import jakarta.transaction.Transactional;

@Service
public class RentalService {

    final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Transactional
    public RentalModel save(RentalModel rentalModel) {
        return rentalRepository.save(rentalModel);
    }

    public Page<RentalModel> findAll(Pageable pageable) {
        return rentalRepository.findAll(pageable);
    }

    public Optional<RentalModel> findById(UUID id) {
        return rentalRepository.findById(id);
    }

    @Transactional
    public void delete(RentalModel rentalModel) {
        rentalRepository.delete(rentalModel);
    }

}
