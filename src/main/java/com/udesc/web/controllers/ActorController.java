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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udesc.web.dtos.ActorDto;
import com.udesc.web.models.ActorModel;
import com.udesc.web.services.ActorService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/actor")
public class ActorController {

    final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<Object> createActor(@RequestBody @Valid ActorDto actorDto) {
        var actorModel = new ActorModel();
        BeanUtils.copyProperties(actorDto, actorModel);

        var localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        actorModel.setCreatedAt(localDateTime);
        actorModel.setUpdatedAt(localDateTime);

        return ResponseEntity.status(HttpStatus.CREATED).body(actorService.save(actorModel));
    }

    @GetMapping
    public ResponseEntity<Page<ActorModel>> getActors(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getActorById(@PathVariable(value = "id") UUID id) {
        Optional<ActorModel> actorModel = actorService.findById(id);

        if (!actorModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(actorModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteActorById(@PathVariable(value = "id") UUID id) {
        Optional<ActorModel> actorModelOptional = actorService.findById(id);

        if (!actorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found");
        }

        actorService.delete(actorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Actor deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateActorById(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ActorDto actorDto) {
        Optional<ActorModel> actorModelOptional = actorService.findById(id);

        if (!actorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found");
        }

        var actorModel = new ActorModel();
        BeanUtils.copyProperties(actorDto, actorModel);

        actorModel.setId(id);
        actorModel.setCreatedAt(actorModelOptional.get().getCreatedAt());
        actorModel.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.OK).body(actorService.save(actorModel));
    }

}
