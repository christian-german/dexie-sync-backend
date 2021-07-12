package com.example.dexiesyncbackend.repository;

import com.example.dexiesyncbackend.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends PagingAndSortingRepository<BookEntity, UUID>, JpaSpecificationExecutor<BookEntity> {
    @Override
    List<BookEntity> findAll();

    List<BookEntity> getBookByRevisionGreaterThanAndRevisionLessThanAndUpdatedByClientIdNot(Long revisionFrom, Long revisionTo, Long clientId);
}
