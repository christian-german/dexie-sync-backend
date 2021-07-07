package com.example.dexiesyncbackend.controller;

import com.example.dexiesyncbackend.dto.AuthorDTO;
import com.example.dexiesyncbackend.mapper.AuthorMapper;
import com.example.dexiesyncbackend.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping
    public Iterable<AuthorDTO> getAllAuthors() {
        return authorMapper.toDtos(authorService.findAll());
    }
}
