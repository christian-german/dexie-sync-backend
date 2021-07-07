package com.example.dexiesyncbackend.controller;

import com.example.dexiesyncbackend.service.sync.SynchronizationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/identity")
public class ClientIdentityController {

    private final SynchronizationService synchronizationService;

    @GetMapping
    public Long getNextClientIdentity() {
        return synchronizationService.getNewClientIdentity();
    }
}
