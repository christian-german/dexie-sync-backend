package com.example.dexiesyncbackend.controller;

import com.example.dexiesyncbackend.dto.BookDTO;
import com.example.dexiesyncbackend.mapper.BookMapper;
import com.example.dexiesyncbackend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookDTO> getAllAuthors() {
        return bookMapper.toDtos(bookService.findAll());
    }
}
