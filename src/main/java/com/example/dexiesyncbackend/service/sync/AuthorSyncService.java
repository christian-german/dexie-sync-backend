package com.example.dexiesyncbackend.service.sync;

import com.example.dexiesyncbackend.dto.sync.DatabaseChangeDTO;
import com.example.dexiesyncbackend.dto.sync.DatabaseChangeTypeEnum;
import com.example.dexiesyncbackend.entity.AuthorEntity;
import com.example.dexiesyncbackend.mapper.AuthorMapper;
import com.example.dexiesyncbackend.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorSyncService {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public List<DatabaseChangeDTO> getAuthorsChanges(Long revisionFrom, Long revisionTo, Long clientIdentity) {
        return authorService.findAllChanges(revisionFrom, revisionTo, clientIdentity)
                .stream().map(this::convertToDatabaseChange).collect(Collectors.toList());
    }

    private DatabaseChangeDTO convertToDatabaseChange(AuthorEntity authorEntity) {
        return new DatabaseChangeDTO(
                DatabaseChangeTypeEnum.CREATE,
                "authors",
                authorEntity.getId().toString(),
                authorMapper.toDto(authorEntity),
                authorEntity.getRevision());
    }
}
