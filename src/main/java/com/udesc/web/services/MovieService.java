package com.udesc.web.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.udesc.web.models.MovieModel;
import com.udesc.web.repositories.MovieRepository;

import jakarta.transaction.Transactional;

@Service
public class MovieService {

    final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Object save(MovieModel movieModel) {
        return movieRepository.save(movieModel);
    }

    public Page<MovieModel> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

}