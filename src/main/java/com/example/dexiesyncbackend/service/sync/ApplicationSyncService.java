package com.example.dexiesyncbackend.service.sync;

import com.example.dexiesyncbackend.dto.sync.DatabaseChangeDTO;
import com.example.dexiesyncbackend.dto.sync.DatabaseChangeTypeEnum;
import com.example.dexiesyncbackend.entity.ApplicationEntity;
import com.example.dexiesyncbackend.mapper.ApplicationMapper;
import com.example.dexiesyncbackend.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationSyncService {

    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;

    public List<DatabaseChangeDTO> getAuthorsChanges(Long revisionFrom, Long revisionTo, Long clientIdentity) {
        return applicationService.findAllChanges(revisionFrom, revisionTo, clientIdentity)
                .stream().map(this::convertToDatabaseChange).collect(Collectors.toList());
    }

    private DatabaseChangeDTO convertToDatabaseChange(ApplicationEntity applicationEntity) {
        return new DatabaseChangeDTO(
                DatabaseChangeTypeEnum.CREATE,
                "application",
                applicationEntity.getId().toString(),
                applicationMapper.toDto(applicationEntity),
                applicationEntity.getRevision());
    }
}
