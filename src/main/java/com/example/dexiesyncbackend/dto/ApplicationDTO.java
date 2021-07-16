package com.example.dexiesyncbackend.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ApplicationDTO {
    private UUID id;
    private String libelle;
    private String chemin;
    private String description;
    private Boolean online;
    private Boolean offline;
    private Boolean mobile;
    private Boolean active;
    private Integer categorie_application_id;
    private Integer fichier_id;
    private Timestamp modified_date;
    private String modified_by;
    private Integer version;
}
