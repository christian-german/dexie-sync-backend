package com.example.dexiesyncbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicInsert
@Table(name = "book")
public class BookEntity extends AbstractSynchronizable {
    private String title;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
}
