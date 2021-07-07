package com.example.dexiesyncbackend.service.sync;

import com.example.dexiesyncbackend.dto.sync.DatabaseChangeDTO;
import com.example.dexiesyncbackend.dto.sync.DatabaseChangeTypeEnum;
import com.example.dexiesyncbackend.entity.BookEntity;
import com.example.dexiesyncbackend.mapper.BookMapper;
import com.example.dexiesyncbackend.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookSyncService {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public List<DatabaseChangeDTO> getBooksChanges(Long syncedRevision, Long clientIdentity) {
        return bookService.findAllChanges(syncedRevision, clientIdentity)
                .stream().map(this::convertToDatabaseChange).collect(Collectors.toList());
    }

    private DatabaseChangeDTO convertToDatabaseChange(BookEntity bookEntity) {
        return new DatabaseChangeDTO(
                DatabaseChangeTypeEnum.CREATE,
                "books",
                bookEntity.getId().toString(),
                bookMapper.toDto(bookEntity));
    }
}
