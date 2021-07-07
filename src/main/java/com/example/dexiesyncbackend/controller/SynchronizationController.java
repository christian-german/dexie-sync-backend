package com.example.dexiesyncbackend.controller;

import com.example.dexiesyncbackend.dto.sync.SyncRequestDTO;
import com.example.dexiesyncbackend.dto.sync.SyncResponseDTO;
import com.example.dexiesyncbackend.service.sync.SynchronizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class SynchronizationController {

    private final SynchronizationService synchronizationService;

    @PostMapping("sync")
    public SyncResponseDTO synchronize(@RequestBody SyncRequestDTO syncRequestDTO) {
        log.info("Receiving request: {}", syncRequestDTO.toString());
        return synchronizationService.synchronize(
                syncRequestDTO.getSyncedRevision(),
                syncRequestDTO.getClientIdentity(),
                syncRequestDTO.getChanges());
    }
}
