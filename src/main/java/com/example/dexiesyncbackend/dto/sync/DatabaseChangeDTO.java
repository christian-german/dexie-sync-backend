package com.example.dexiesyncbackend.dto.sync;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DatabaseChangeDTO {
    private DatabaseChangeTypeEnum type;
    private String table;
    private String key;
    private Object obj;
}
