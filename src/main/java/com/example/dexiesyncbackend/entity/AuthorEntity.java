package com.example.dexiesyncbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "author")
public class AuthorEntity extends AbstractSynchronizable {
    private String firstname;
    private String lastname;
}
