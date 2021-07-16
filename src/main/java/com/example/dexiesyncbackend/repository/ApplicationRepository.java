package com.example.dexiesyncbackend.repository;

import com.example.dexiesyncbackend.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<ApplicationEntity, UUID>, JpaSpecificationExecutor<ApplicationEntity> {
    @Override
    List<ApplicationEntity> findAll();

    List<ApplicationEntity> getAuthorByRevisionGreaterThanAndRevisionLessThanEqualAndUpdatedByClientIdNot(Long revisionFrom, Long revisionTo, Long clientId);
}
