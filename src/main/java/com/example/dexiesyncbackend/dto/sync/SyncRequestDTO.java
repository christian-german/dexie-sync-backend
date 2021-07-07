package com.example.dexiesyncbackend.dto.sync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyncRequestDTO {
    private Long baseRevision;
    private List<DatabaseChangeDTO> changes;
    private Long clientIdentity;
    private Long syncedRevision;
    private Long requestId;
    private boolean partial;
    private boolean success;
}
