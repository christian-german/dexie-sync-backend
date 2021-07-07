package com.example.dexiesyncbackend.service;

import com.example.dexiesyncbackend.entity.AuthorEntity;
import com.example.dexiesyncbackend.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorEntity> findAllChanges(Long syncedRevision, Long clientIdentity) {
        return authorRepository.getAuthorByRevisionGreaterThanAndUpdatedByClientIdNot(syncedRevision, clientIdentity);
    }

    public List<AuthorEntity> findAll() {
        return authorRepository.findAll();
    }
}
