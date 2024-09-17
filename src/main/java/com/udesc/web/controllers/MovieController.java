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

import com.udesc.web.dtos.MovieDto;
import com.udesc.web.models.ActorModel;
import com.udesc.web.models.MovieModel;
import com.udesc.web.services.ActorService;
import com.udesc.web.services.MovieService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/movie")
public class MovieController {

    final MovieService movieService;
    final ActorService actorService;

    public MovieController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<Object> createMovie(@RequestBody @Valid MovieDto movieDto) {
        var movieModel = new MovieModel();
        BeanUtils.copyProperties(movieDto, movieModel);

        var localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        movieModel.setCreatedAt(localDateTime);
        movieModel.setUpdatedAt(localDateTime);

        if (movieDto.getActorIds() != null) {
            movieDto.getActorIds().forEach(actorId -> {
                ActorModel actor = actorService.findById(actorId)
                        .orElseThrow(() -> new RuntimeException("Actor not found with ID: " + actorId));
                movieModel.addActor(actor);
            });
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movieModel));
    }

    @GetMapping
    public ResponseEntity<Page<MovieModel>> getMovies(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable(value = "id") UUID id) {
        Optional<MovieModel> movieModel = movieService.findById(id);

        if (!movieModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movieModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovieById(@PathVariable(value = "id") UUID id) {
        Optional<MovieModel> movieModelOptional = movieService.findById(id);

        if (!movieModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }

        movieService.delete(movieModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted successfully");
    }

}