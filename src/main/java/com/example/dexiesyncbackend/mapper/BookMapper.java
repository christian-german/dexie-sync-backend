package com.example.dexiesyncbackend.mapper;

import com.example.dexiesyncbackend.dto.BookDTO;
import com.example.dexiesyncbackend.entity.BookEntity;
import com.example.dexiesyncbackend.repository.AuthorRepository;
import com.example.dexiesyncbackend.repository.BookRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class BookMapper {

    @Autowired
    private AuthorRepository authorRepository;

    @Mappings({
            @Mapping(target = "authorId", source = "authorEntity.id"),
    })
    public abstract BookDTO toDto(BookEntity bookEntity);

    public abstract BookEntity toEntity(BookDTO bookDTO);

    public abstract List<BookDTO> toDtos(List<BookEntity> bookEntityEntities);

    public abstract List<BookEntity> toEntities(List<BookDTO> bookDTOS);

    @BeforeMapping
    protected void enrichEntityWithAuthorEntity(BookDTO bookDTO, @MappingTarget BookEntity bookEntity) {
        bookEntity.setAuthorEntity(authorRepository.findById(bookDTO.getAuthorId()).get());
    }
}
