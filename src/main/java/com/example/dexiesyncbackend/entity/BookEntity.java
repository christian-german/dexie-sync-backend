package com.example.dexiesyncbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book")
public class BookEntity extends AbstractSynchronizable {
    private String title;
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
}
