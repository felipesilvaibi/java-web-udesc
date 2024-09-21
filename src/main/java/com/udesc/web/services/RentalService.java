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

        if (stock.getQuantity() - stock.getRented() <= 0) {
            throw new RuntimeException("No available stock for this movie");
        }

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

    @Transactional
    public RentalModel update(RentalModel existingRental, RentalModel updatedRental) {
        StockModel oldStock = existingRental.getStock();
        StockModel newStock = updatedRental.getStock();

        if (!oldStock.getId().equals(newStock.getId())) {
            oldStock.setRented(oldStock.getRented() - 1);
            stockService.save(oldStock);

            if (newStock.getQuantity() - newStock.getRented() <= 0) {
                throw new RuntimeException("No available stock for this movie");
            }

            newStock.setRented(newStock.getRented() + 1);
            stockService.save(newStock);
        }

        existingRental.setCustomer(updatedRental.getCustomer());
        existingRental.setStock(newStock);
        existingRental.setRentalDate(updatedRental.getRentalDate());
        existingRental.setReturnDate(updatedRental.getReturnDate());
        existingRental.setUpdatedAt(updatedRental.getUpdatedAt());

        return rentalRepository.save(existingRental);
    }

}
