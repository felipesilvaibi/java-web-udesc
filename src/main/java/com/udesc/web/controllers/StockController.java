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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udesc.web.dtos.StockDto;
import com.udesc.web.models.MovieModel;
import com.udesc.web.models.StockModel;
import com.udesc.web.services.MovieService;
import com.udesc.web.services.StockService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/stock")
public class StockController {

    final StockService stockService;
    final MovieService movieService;

    public StockController(StockService stockService, MovieService movieService) {
        this.stockService = stockService;
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Object> createStock(@RequestBody @Valid StockDto stockDto) {
        Optional<MovieModel> movieOptional = movieService.findById(stockDto.getMovieId());
        if (!movieOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }

        MovieModel movie = movieOptional.get();

        if (stockService.existsByMovie(movie)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Stock already exists for this movie");
        }

        var stockModel = new StockModel();
        BeanUtils.copyProperties(stockDto, stockModel);
        stockModel.setMovie(movie);

        var localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        stockModel.setCreatedAt(localDateTime);
        stockModel.setUpdatedAt(localDateTime);

        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.save(stockModel));
    }

    @GetMapping
    public ResponseEntity<Page<StockModel>> getStocks(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(stockService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStockById(@PathVariable(value = "id") UUID id) {
        Optional<StockModel> stockOptional = stockService.findById(id);
        if (!stockOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(stockOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStockById(@PathVariable(value = "id") UUID id) {
        Optional<StockModel> stockOptional = stockService.findById(id);
        if (!stockOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found");
        }
        stockService.delete(stockOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Stock deleted successfully");
    }

}
