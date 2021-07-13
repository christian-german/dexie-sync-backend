package com.example.dexiesyncbackend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbstractSynchronizable {
    @Id
    private UUID id = UUID.randomUUID();
    @Column(columnDefinition = "bigint NOT NULL DEFAULT nextval('server_revision_seq')")
    private Long revision;
    private Long updatedByClientId;
}
