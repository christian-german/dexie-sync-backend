package com.example.dexiesyncbackend.service;

import com.example.dexiesyncbackend.entity.ApplicationEntity;
import com.example.dexiesyncbackend.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public List<ApplicationEntity> findAllChanges(Long revisionFrom, Long revisionTo, Long clientIdentity) {
        return applicationRepository.getAuthorByRevisionGreaterThanAndRevisionLessThanEqualAndUpdatedByClientIdNot(revisionFrom, revisionTo, clientIdentity);
    }

    public List<ApplicationEntity> findAll() {
        return applicationRepository.findAll();
    }
}
