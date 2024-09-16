package com.udesc.web.services;

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

}