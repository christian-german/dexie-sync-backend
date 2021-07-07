package com.example.dexiesyncbackend.dto.sync;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SyncRequestDTO {
    private Long baseRevision;
    private List<DatabaseChangeDTO> changes;
    private Long clientIdentity;
    private Long syncedRevision;
    private Long requestId;
    private boolean partial;
    private boolean success;
}
