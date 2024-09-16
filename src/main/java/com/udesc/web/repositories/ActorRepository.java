package com.udesc.web.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udesc.web.models.ActorModel;

@Repository
public interface ActorRepository extends JpaRepository<ActorModel, UUID> {
}