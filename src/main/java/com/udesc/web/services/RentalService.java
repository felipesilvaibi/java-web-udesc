package com.udesc.web.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.udesc.web.models.RentalModel;
import com.udesc.web.models.StockModel;
import com.udesc.web.repositories.RentalRepository;

import jakarta.transaction.Transactional;

@Service
public class RentalService {

    final RentalRepository rentalRepository;
    final StockService stockService;

    public RentalService(RentalRepository rentalRepository, StockService stockService) {
        this.rentalRepository = rentalRepository;
        this.stockService = stockService;
    }

    @Transactional
    public RentalModel save(RentalModel rentalModel) {
        StockModel stock = rentalModel.getStock();
        stock.setRented(stock.getRented() + 1);
        stockService.save(stock);

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
        StockModel stock = rentalModel.getStock();
        stock.setRented(stock.getRented() - 1);
        stockService.save(stock);

        rentalRepository.delete(rentalModel);
    }

}
