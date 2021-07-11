package com.example.dexiesyncbackend.repository;

import com.example.dexiesyncbackend.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<AuthorEntity, UUID>, JpaSpecificationExecutor<AuthorEntity> {
    @Override
    List<AuthorEntity> findAll();

    List<AuthorEntity> getAuthorByRevisionGreaterThanAndRevisionLessThanEqualAndUpdatedByClientIdNot(Long revisionFrom, Long revisionTo, Long clientId);
}
