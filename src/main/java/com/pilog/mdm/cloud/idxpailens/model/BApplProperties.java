package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "B_APPL_PROPERTIES")
@Data
public class BApplProperties {
    @Id
    @Column(name = "KEYNAME", length = 50)
    private String keyName;

    @Column(name = "PROCESS_VALUE", length = 4000)
    private String processValue;

    @Column(name = "HEADER", length = 1000)
    private String header;

    @Column(name = "COMP_ID", length = 20)
    private String compId;

    @Column(name = "AUDIT_ID", length = 100)
    private String auditId;
}