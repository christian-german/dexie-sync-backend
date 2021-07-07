package com.example.dexiesyncbackend.service;

import com.example.dexiesyncbackend.entity.BookEntity;
import com.example.dexiesyncbackend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookEntity> findAllChanges(Long syncedRevision, Long clientIdentity) {
        return bookRepository.getBookByRevisionGreaterThanAndUpdatedByClientIdNot(syncedRevision, clientIdentity);
    }

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }
}
