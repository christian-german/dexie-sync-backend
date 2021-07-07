package com.example.dexiesyncbackend.dto.sync;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SyncResponseDTO {
    private Long currentRevision;
    private List<DatabaseChangeDTO> changes;
    private Long clientIdentity;
    private boolean partial;
    private boolean success;
    private String errorMessage;
}
