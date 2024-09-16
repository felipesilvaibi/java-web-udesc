package com.udesc.web.services;

import org.springframework.stereotype.Service;

import com.udesc.web.repositories.MovieActorRepository;

@Service
public class MovieActorService {

    final MovieActorRepository movieActorRepository;

    public MovieActorService(MovieActorRepository movieActorRepository) {
        this.movieActorRepository = movieActorRepository;
    }

}
