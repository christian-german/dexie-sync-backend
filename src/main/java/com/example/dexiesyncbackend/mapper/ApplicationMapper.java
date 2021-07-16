package com.example.dexiesyncbackend.mapper;

import com.example.dexiesyncbackend.dto.ApplicationDTO;
import com.example.dexiesyncbackend.entity.ApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ApplicationMapper {
    ApplicationDTO toDto(ApplicationEntity applicationEntity);

    ApplicationEntity toEntity(ApplicationDTO applicationDTO);

    Iterable<ApplicationDTO> toDtos(Iterable<ApplicationEntity> authorEntities);

    Iterable<ApplicationEntity> toEntities(Iterable<ApplicationDTO> authorDTOS);
}
