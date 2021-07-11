package com.example.dexiesyncbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicInsert
@Table(name = "author")
public class AuthorEntity extends AbstractSynchronizable {
    private String firstname;
    private String lastname;
}
