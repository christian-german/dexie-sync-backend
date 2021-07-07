package com.example.dexiesyncbackend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BookDTO {
    private UUID id;
    private String title;
    private UUID authorId;
}
