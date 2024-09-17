package com.udesc.web.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.udesc.web.models.ActorModel;
import com.udesc.web.models.MovieModel;
import com.udesc.web.repositories.MovieRepository;

import jakarta.transaction.Transactional;

@Service
public class MovieService {

    final MovieRepository movieRepository;
    final ActorService actorService;

    public MovieService(MovieRepository movieRepository, ActorService actorService) {
        this.movieRepository = movieRepository;
        this.actorService = actorService;
    }

    @Transactional
    public Object save(MovieModel movieModel) {
        return movieRepository.save(movieModel);
    }

    public Page<MovieModel> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Optional<MovieModel> findById(UUID id) {
        return movieRepository.findById(id);
    }

    @Transactional
    public void delete(MovieModel movieModel) {
        movieRepository.delete(movieModel);
    }

    @Transactional
    public MovieModel updateMovie(MovieModel movieModel, List<UUID> actorIds) {
        movieRepository.save(movieModel);
        movieRepository.flush();

        if (actorIds != null) {
            for (UUID actorId : actorIds) {
                ActorModel actor = actorService.findById(actorId)
                        .orElseThrow(() -> new RuntimeException("Actor not found with ID: " + actorId));
                movieModel.addActor(actor);
            }
        }

        return movieRepository.save(movieModel);
    }

}