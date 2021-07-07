package com.example.dexiesyncbackend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthorDTO {
    private UUID id;
    private String firstname;
    private String lastname;
}
