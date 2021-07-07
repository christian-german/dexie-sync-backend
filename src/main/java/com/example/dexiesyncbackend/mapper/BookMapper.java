package com.example.dexiesyncbackend.mapper;

import com.example.dexiesyncbackend.dto.BookDTO;
import com.example.dexiesyncbackend.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BookMapper {
    @Mappings({
            @Mapping(target = "authorId", source = "authorEntity.id"),
    })
    BookDTO toDto(BookEntity bookEntity);

    BookEntity toEntity(BookDTO bookDTO);

    List<BookDTO> toDtos(List<BookEntity> bookEntityEntities);

    List<BookEntity> toEntities(List<BookDTO> bookDTOS);
}
