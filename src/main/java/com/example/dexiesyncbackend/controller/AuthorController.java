package com.example.dexiesyncbackend.controller;

import com.example.dexiesyncbackend.dto.ApplicationDTO;
import com.example.dexiesyncbackend.mapper.ApplicationMapper;
import com.example.dexiesyncbackend.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    @GetMapping
    public Iterable<ApplicationDTO> getAllAuthors() {
        return applicationMapper.toDtos(applicationService.findAll());
    }
}
