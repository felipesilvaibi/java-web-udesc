package com.udesc.web.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.udesc.web.models.MovieModel;
import com.udesc.web.models.StockModel;
import com.udesc.web.repositories.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class StockService {

    final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public StockModel save(StockModel stockModel) {
        return stockRepository.save(stockModel);
    }

    public Optional<StockModel> findByMovie(MovieModel movie) {
        return stockRepository.findByMovie(movie);
    }

    public boolean existsByMovie(MovieModel movie) {
        return stockRepository.existsByMovie(movie);
    }
}
