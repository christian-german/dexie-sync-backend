package com.example.dexiesyncbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicInsert
@Table(name = "application")
public class ApplicationEntity extends AbstractSynchronizable {
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
