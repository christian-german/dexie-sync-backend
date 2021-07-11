package com.example.dexiesyncbackend.controller;

import com.example.dexiesyncbackend.repository.SynchronizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/identity")
public class ClientIdentityController {

    private final SynchronizationRepository synchronizationRepository;

    @GetMapping
    public Long getNextClientIdentity() {
        return synchronizationRepository.getNewClientIdentity();
    }
}
