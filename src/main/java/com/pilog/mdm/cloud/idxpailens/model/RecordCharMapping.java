package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "O_RECORD_CHAR_MAPPING")
@Data
public class RecordCharMapping {

    @Id
    @Column(name = "AUDIT_ID", length = 1000)
    private String auditId;
    @Column(name = "CLASS_TERM", length = 1000)
    private String classTerm;

    @Column(name = "AI_PROPERTY_NAME", length = 200, nullable = false)
    private String aiPropertyName;

    @Column(name = "PPO_PROPERTY_VALUE", length = 1000)
    private String ppoPropertyValue;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "EDIT_DATE")
    private LocalDateTime editDate;

    @Column(name = "PROJECT_NAME", length = 100)
    private String projectName;

    @Column(name = "DATA_TYPE", length = 100)
    private String dataType;


    @Column(name = "EDIT_BY", length = 200)
    private String editBy;

    @Column(name = "ASSIGNED_TO", length = 200)
    private String assignedTo;

    @Column(name = "BATCH_ID", length = 500)
    private String batchId;

    @Column(name = "CUSTOM_COLUMN1", length = 500)
    private String customColumn1;

    @Column(name = "CUSTOM_COLUMN2", length = 500)
    private String customColumn2;
}
