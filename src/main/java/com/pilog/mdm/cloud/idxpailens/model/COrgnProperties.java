package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "C_ORGN_PROPERTIES")
public class COrgnProperties {

    @Id
    @Column(name = "ORGN_ID" ,columnDefinition = "RAW(16)")
    private String orgnId;

    @Column(name = "DOMAIN", length = 30)
    private String domain;

    @Column(name = "PROPERTY_NAME", length = 50)
    private String propertyName;

    @Column(name = "PROPERTY_VALUE", length = 500)
    private String propertyValue;

    @Column(name = "AUDIT_ID", length = 100)
    private String auditId;

    @Column(name = "CREATE_BY", length = 50)
    private String createBy;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "EDIT_DATE")
    private LocalDateTime editDate;
}
