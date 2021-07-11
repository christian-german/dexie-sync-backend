package com.example.dexiesyncbackend.dto.sync;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseChangeDTO {
    private DatabaseChangeTypeEnum type;
    private String table;
    private String key;
    private Object obj;
    @JsonIgnore
    private Long revision;
}
