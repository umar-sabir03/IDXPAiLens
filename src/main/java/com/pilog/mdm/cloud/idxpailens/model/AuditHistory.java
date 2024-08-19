package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "AUDIT_HISTORY")
@Data
public class AuditHistory {

    @Id
    @Column(name = "AUDIT_ID", length = 240)
    private String auditId;

    @Column(name = "ORGN_ID", columnDefinition = "RAW")
    private String orgnId;

    @Column(name = "PLANT", length = 120)
    private String plant;

    @Column(name = "AUDIT_SEQ", length = 200)
    private String auditSeq;

    @Column(name = "DML_TYPE", length = 40)
    private String dmlType;

    @Column(name = "TABLE_NAME", length = 128)
    private String tableName;

    @Column(name = "COLUMN_NAME", length = 200)
    private String columnName;

    @Column(name = "OLD_VALUE", length = 4000)
    private String oldValue;

    @Column(name = "NEW_VALUE", length = 4000)
    private String newValue;

    @Column(name = "AUDIT_DATE")
    private LocalDate auditDate;

    @Column(name = "SESSION_ID", length = 600)
    private String sessionId;

    @Column(name = "AUDIT_BY", length = 200)
    private String auditBy;

    @Column(name = "INSTANCE", length = 120)
    private String instance;
}
