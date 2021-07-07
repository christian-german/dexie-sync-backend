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
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SynchronizationService {

    private final AuthorSyncService authorSyncService;
    private final BookSyncService bookSyncService;
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    public SyncResponseDTO synchronize(Long syncedRevision, Long clientIdentity, List<DatabaseChangeDTO> clientChanges) {

        // Generates a client identity if needed.
        clientIdentity = (clientIdentity != null) ? clientIdentity : getNewClientIdentity();

        // Assumes syncedRevision is 0 if empty.
        syncedRevision = (syncedRevision != null) ? syncedRevision : 0L;

        // First, apply changes to the server DB.
        handleClientChanges(clientChanges, syncedRevision, clientIdentity);

        // Query DB to get server changes, starting from syncedRevision, and not updated by the requesting client.
        List<DatabaseChangeDTO> serverChanges = getServerChanges(syncedRevision, clientIdentity);

        return new SyncResponseDTO(
                syncedRevision + serverChanges.size(),
                serverChanges,
                clientIdentity,
                false,
                true,
                null
        );
    }

    private void handleClientChanges(List<DatabaseChangeDTO> clientChanges, Long revision, Long clientIdentity) {
        clientChanges.forEach(databaseChangeDTO -> {
            //TODO: Generify the DB target table.
            if ("authors".equals(databaseChangeDTO.getTable())) {
                switch (databaseChangeDTO.getType()) {
                    case CREATE:
                    case UPDATE:
                        AuthorDTO authorDTO = objectMapper.convertValue(databaseChangeDTO.getObj(), AuthorDTO.class);
                        AuthorEntity authorEntity = authorMapper.toEntity(authorDTO);
                        authorEntity.setRevision(revision);
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
                        bookEntity.setRevision(revision);
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

    private List<DatabaseChangeDTO> getServerChanges(Long syncedRevision, Long clientIdentity) {
        List<DatabaseChangeDTO> changes = new ArrayList<>();

        changes.addAll(authorSyncService.getAuthorsChanges(syncedRevision, clientIdentity));
        changes.addAll(bookSyncService.getBooksChanges(syncedRevision, clientIdentity));

        return changes;
    }

    public Long getNewClientIdentity() {
        Query query = entityManager.createNativeQuery("SELECT nextval('client_identity_seq')");
        return ((BigInteger) query.getSingleResult()).longValue();
    }
}
