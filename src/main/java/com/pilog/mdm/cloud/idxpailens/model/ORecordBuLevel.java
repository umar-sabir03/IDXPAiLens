package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "O_RECORD_BU_LEVEL")
@Data
public class ORecordBuLevel {
        @Id
        @Column(name = "AUDIT_ID", length = 100)
        private String auditId;

        @Column(name = "RECORD_NO", length = 20)
        private String recordNo;

        @Column(name = "REGION", length = 20)
        private String region;

        @Column(name = "LOCALE", length = 5)
        private String locale;

        @Column(name = "BUSINESS_UNIT", length = 20)
        private String businessUnit;

        @Column(name = "INSTANCE", length = 30)
        private String instance;

        @Column(name = "STATUS", length = 30)
        private String status;

        @Column(name = "ERP_COMMENT", length = 4000)
        private String erpComment;

        @Column(name = "ERP_TRANSFERRED_BY", length = 50)
        private String erpTransferredBy;

        @Column(name = "ERP_TRANSFERRED_DATE")
        private LocalDate erpTransferredDate;

        @Column(name = "CREATE_DATE")
        private LocalDate createDate;

        @Column(name = "CREATE_BY", length = 50)
        private String createBy;

        @Column(name = "EDIT_DATE")
        private LocalDate editDate;

        @Column(name = "EDIT_BY", length = 50)
        private String editBy;



        @Column(name = "STEPS")
        private Integer steps;

        @Column(name = "APPROVED_BY", length = 50)
        private String approvedBy;

        @Column(name = "APPROVED_DATE")
        private LocalDate approvedDate;

        @Column(name = "SUPERCEDED_BY", length = 50)
        private String supercededBy;

        @Column(name = "DELETION_FLAG", length = 1)
        private String deletionFlag;

        @Column(name = "RECORD_DQ_LEVEL", length = 4)
        private String recordDqLevel;

        @Column(name = "RECORD_TRUST_LEVEL", length = 4)
        private String recordTrustLevel;

        @Column(name = "RECORD_STATUS", length = 20)
        private String recordStatus;

        @Column(name = "R_QL", length = 50)
        private String rQl;

        @Column(name = "R_TL", length = 50)
        private String rTl;

        @Column(name = "R_ST", length = 50)
        private String rSt;

        @Column(name = "PROJECT", length = 20)
        private String project;

        @Column(name = "ENTITY", length = 50)
        private String entity;

        @Column(name = "DOC_NO", length = 100)
        private String docNo;

        @Column(name = "MODIFY_FLAG", length = 1)
        private String modifyFlag;

        @Column(name = "REQUEST_DESCR", length = 500)
        private String requestDescr;

        @Column(name = "SOURCE", length = 100)
        private String source;

        @Column(name = "DATA_SOURCE", length = 100)
        private String dataSource;

        @Column(name = "RPRT_SOURCE", length = 10)
        private String rprtSource;

        @Column(name = "TEST_CERTIFICATE", length = 50)
        private String testCertificate;

        @Column(name = "TEST_CERT_COMMENT", length = 4000)
        private String testCertComment;

        @Column(name = "URGENCY_IND", length = 50)
        private String urgencyInd;

        @Column(name = "URGENCY_COMMENTS", length = 4000)
        private String urgencyComments;

        @Column(name = "CUSTOM_COLUMN1_DATE")
        private LocalDate customColumn1Date;

        @Column(name = "CUSTOM_COLUMN2_DATE")
        private LocalDate customColumn2Date;

        @Column(name = "CUSTOM_COLUMN3_DATE")
        private LocalDate customColumn3Date;

        @Column(name = "CUSTOM_COLUMN4_DATE")
        private LocalDate customColumn4Date;

        @Column(name = "CUSTOM_COLUMN5", length = 400)
        private String customColumn5;

        @Column(name = "CUSTOM_COLUMN6", length = 400)
        private String customColumn6;

        @Column(name = "CUSTOM_COLUMN7", length = 400)
        private String customColumn7;

        @Column(name = "CUSTOM_COLUMN8", length = 400)
        private String customColumn8;

        @Column(name = "CUSTOM_COLUMN9", length = 400)
        private String customColumn9;

        @Column(name = "CUSTOM_COLUMN10", length = 400)
        private String customColumn10;

        @Column(name = "CUSTOM_COLUMN11", length = 400)
        private String customColumn11;

        @Column(name = "CUSTOM_COLUMN12", length = 400)
        private String customColumn12;

        @Column(name = "CUSTOM_COLUMN13", length = 400)
        private String customColumn13;

        @Column(name = "CUSTOM_COLUMN14", length = 400)
        private String customColumn14;

        @Column(name = "CUSTOM_COLUMN15", length = 400)
        private String customColumn15;

        @Column(name = "BU_CUST_COL16", length = 4000)
        private String buCustCol16;

        @Column(name = "BU_CUST_COL17", length = 4000)
        private String buCustCol17;

        @Column(name = "BU_CUST_COL18", length = 4000)
        private String buCustCol18;

        @Column(name = "BU_CUST_COL19", length = 4000)
        private String buCustCol19;

        @Column(name = "BU_CUST_COL20", length = 4000)
        private String buCustCol20;

        @Column(name = "BU_CUST_COL21", length = 4000)
        private String buCustCol21;

        @Column(name = "BU_CUST_COL22", length = 4000)
        private String buCustCol22;

        @Column(name = "BU_CUST_COL23", length = 4000)
        private String buCustCol23;

        @Column(name = "BU_CUST_COL24", length = 4000)
        private String buCustCol24;

        @Column(name = "BU_CUST_COL25", length = 4000)
        private String buCustCol25;

        @Column(name = "BU_CUST_COL26", length = 4000)
        private String buCustCol26;

        @Column(name = "BU_CUST_COL27", length = 4000)
        private String buCustCol27;

        @Column(name = "BU_CUST_COL28", length = 4000)
        private String buCustCol28;

        @Column(name = "BU_CUST_COL29", length = 4000)
        private String buCustCol29;

        @Column(name = "BU_CUST_COL30", length = 4000)
        private String buCustCol30;

        @Column(name = "BU_CUST_COL31", length = 4000)
        private String buCustCol31;

        @Column(name = "BU_CUST_COL32", length = 4000)
        private String buCustCol32;

        @Column(name = "BU_CUST_COL33", length = 4000)
        private String buCustCol33;

        @Column(name = "BU_CUST_COL34", length = 4000)
        private String buCustCol34;

        @Column(name = "BU_CUST_COL35", length = 4000)
        private String buCustCol35;

    }
