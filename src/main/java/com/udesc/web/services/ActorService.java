package com.udesc.web.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.udesc.web.models.ActorModel;
import com.udesc.web.repositories.ActorRepository;

import jakarta.transaction.Transactional;

@Service
public class ActorService {

    final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Transactional
    public Object save(ActorModel actorModel) {
        return actorRepository.save(actorModel);
    }

    public Page<ActorModel> findAll(Pageable pageable) {
        return actorRepository.findAll(pageable);
    }

}
