package com.example.dexiesyncbackend.service.sync;

import com.example.dexiesyncbackend.dto.AuthorDTO;
import com.example.dexiesyncbackend.dto.BookDTO;
import com.example.dexiesyncbackend.dto.sync.DatabaseChangeDTO;
import com.example.dexiesyncbackend.dto.sync.SyncResponseDTO;
import com.example.dexiesyncbackend.entity.AuthorEntity;
import com.example.dexiesyncbackend.entity.BookEntity;
import com.example.dexiesyncbackend.mapper.AuthorMapper;
import com.example.dexiesyncbackend.mapper.BookMapper;
import com.example.dexiesyncbackend.repository.AuthorRepository;
import com.example.dexiesyncbackend.repository.BookRepository;
import com.example.dexiesyncbackend.repository.SynchronizationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SynchronizationService {

    private final AuthorSyncService authorSyncService;
    private final BookSyncService bookSyncService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final SynchronizationRepository synchronizationRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;
    private final ObjectMapper objectMapper;
    @Value("${synchronization.max-changes}")
    private int maxPartialChanges;

    public SyncResponseDTO synchronize(Long syncedRevision, Long clientIdentity, List<DatabaseChangeDTO> clientChanges) {

        // Generates a client identity if needed.
        clientIdentity = (clientIdentity != null) ? clientIdentity : synchronizationRepository.getNewClientIdentity();

        // Assumes syncedRevision is 0 if empty.
        syncedRevision = (syncedRevision != null) ? syncedRevision : 0L;

        // First, apply client's changes to the server DB.
        handleClientChanges(clientChanges, clientIdentity);

        // Fix the server revision to avoid concurrency problems.
        Long fixedServerRevision = synchronizationRepository.getServerRevision();

        // Query DB to get server changes, starting from syncedRevision, and not updated by the requesting client.
        //TODO: Limit the server changes size to maxChanges to avoid useless requests.
        // to do this, take the syncedRevision, add the maxPartialChanges and request with between.
        List<DatabaseChangeDTO> serverChanges = getServerChanges(syncedRevision, fixedServerRevision, clientIdentity);

        // Handle partial changes.
        if (serverChanges.size() > maxPartialChanges) {
            serverChanges.sort(Comparator.comparing(DatabaseChangeDTO::getRevision));
            List<DatabaseChangeDTO> filteredServerChanges = serverChanges.subList(0, maxPartialChanges);
            Long revision = filteredServerChanges.stream()
                    .max(Comparator.comparing(DatabaseChangeDTO::getRevision))
                    .orElseThrow(NoSuchElementException::new)
                    .getRevision();

            return new SyncResponseDTO(
                    revision,
                    filteredServerChanges,
                    clientIdentity,
                    true,
                    true,
                    null
            );
        } else {
            return new SyncResponseDTO(
                    fixedServerRevision,
                    serverChanges,
                    clientIdentity,
                    false,
                    true,
                    null
            );
        }
    }

    private void handleClientChanges(List<DatabaseChangeDTO> clientChanges, Long clientIdentity) {
        // Each change gets a new revision.
        clientChanges.forEach(databaseChangeDTO -> {
            //TODO: Generify the DB target table.
            if ("authors".equals(databaseChangeDTO.getTable())) {
                switch (databaseChangeDTO.getType()) {
                    case CREATE:
                    case UPDATE:
                        AuthorDTO authorDTO = objectMapper.convertValue(databaseChangeDTO.getObj(), AuthorDTO.class);
                        AuthorEntity authorEntity = authorMapper.toEntity(authorDTO);
                        authorEntity.setUpdatedByClientId(clientIdentity);
                        authorRepository.save(authorEntity);
                        break;
                    case DELETE:
                        authorRepository.deleteById(UUID.fromString(databaseChangeDTO.getKey()));
                        break;
                }
            } else if ("books".equals(databaseChangeDTO.getTable())) {
                switch (databaseChangeDTO.getType()) {
                    case CREATE:
                    case UPDATE:
                        BookDTO bookDTO = objectMapper.convertValue(databaseChangeDTO.getObj(), BookDTO.class);
                        BookEntity bookEntity = bookMapper.toEntity(bookDTO);
                        bookEntity.setUpdatedByClientId(clientIdentity);
                        bookRepository.save(bookEntity);
                        break;
                    case DELETE:
                        bookRepository.deleteById(UUID.fromString(databaseChangeDTO.getKey()));
                        break;
                }
            }
        });
    }

    private List<DatabaseChangeDTO> getServerChanges(Long revisionFrom, Long revisionTo, Long clientIdentity) {
        List<DatabaseChangeDTO> changes = new ArrayList<>();

        changes.addAll(authorSyncService.getAuthorsChanges(revisionFrom, revisionTo, clientIdentity));
        changes.addAll(bookSyncService.getBooksChanges(revisionFrom, revisionTo, clientIdentity));

        return changes;
    }
}
