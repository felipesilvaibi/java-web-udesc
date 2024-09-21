package com.udesc.web.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udesc.web.models.MovieModel;
import com.udesc.web.models.StockModel;

@Repository
public interface StockRepository extends JpaRepository<StockModel, UUID> {

    boolean existsByMovie(MovieModel movie);

    Optional<StockModel> findByMovie(MovieModel movie);
}
