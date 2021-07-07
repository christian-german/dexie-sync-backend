package com.example.dexiesyncbackend.mapper;

import com.example.dexiesyncbackend.dto.AuthorDTO;
import com.example.dexiesyncbackend.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AuthorMapper {
    AuthorDTO toDto(AuthorEntity authorEntity);

    AuthorEntity toEntity(AuthorDTO authorDTO);

    Iterable<AuthorDTO> toDtos(Iterable<AuthorEntity> authorEntities);

    Iterable<AuthorEntity> toEntities(Iterable<AuthorDTO> authorDTOS);
}
