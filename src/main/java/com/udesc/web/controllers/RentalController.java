package com.udesc.web.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udesc.web.dtos.RentalDto;
import com.udesc.web.models.CustomerModel;
import com.udesc.web.models.RentalModel;
import com.udesc.web.models.StockModel;
import com.udesc.web.services.CustomerService;
import com.udesc.web.services.MovieService;
import com.udesc.web.services.RentalService;
import com.udesc.web.services.StockService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rental")
public class RentalController {

    final RentalService rentalService;
    final CustomerService customerService;
    final MovieService movieService;
    final StockService stockService;

    public RentalController(RentalService rentalService, CustomerService customerService, MovieService movieService,
            StockService stockService) {
        this.rentalService = rentalService;
        this.customerService = customerService;
        this.movieService = movieService;
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Object> createRental(@RequestBody @Valid RentalDto rentalDto) {
        Optional<CustomerModel> customerOptional = customerService.findById(rentalDto.getCustomerId());
        if (!customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }

        Optional<StockModel> stockOptional = stockService.findById(rentalDto.getStockId());
        if (!stockOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found");
        }

        StockModel stock = stockOptional.get();

        if (stock.getQuantity() - stock.getRented() <= 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No available stock for this movie");
        }

        RentalModel rentalModel = new RentalModel();
        rentalModel.setCustomer(customerOptional.get());
        rentalModel.setStock(stock);

        LocalDate rentalDate = LocalDate.parse(rentalDto.getRentalDate());
        rentalModel.setRentalDate(rentalDate);

        if (rentalDto.getReturnDate() != null) {
            LocalDate returnDate = LocalDate.parse(rentalDto.getReturnDate());
            rentalModel.setReturnDate(returnDate);
        }

        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        rentalModel.setCreatedAt(now);
        rentalModel.setUpdatedAt(now);

        return ResponseEntity.status(HttpStatus.CREATED).body(rentalService.save(rentalModel));
    }

    @GetMapping
    public ResponseEntity<Page<RentalModel>> getRentals(
            @PageableDefault(page = 0, size = 10, sort = "rentalDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(rentalService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRentalById(@PathVariable(value = "id") UUID id) {
        Optional<RentalModel> rentalOptional = rentalService.findById(id);
        if (!rentalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(rentalOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRentalById(@PathVariable(value = "id") UUID id) {
        Optional<RentalModel> rentalOptional = rentalService.findById(id);
        if (!rentalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental not found");
        }

        RentalModel rentalModel = rentalOptional.get();

        rentalService.delete(rentalModel);
        return ResponseEntity.status(HttpStatus.OK).body("Rental deleted successfully");
    }

}
