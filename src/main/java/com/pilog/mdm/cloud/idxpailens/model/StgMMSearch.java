package com.pilog.mdm.cloud.idxpailens.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "STG_MM_SEARCH")
@Data
public class StgMMSearch {

    @Id
    @Column(name = "AUDIT_ID", length = 4000)
    private String auditId;

    @Column(name = "ORGN_ID", columnDefinition = "RAW")
    private String orgnId;

    @Column(name = "RECORD_NO", length = 20)
    private String recordNo;

    @Column(name = "DR_ID1", columnDefinition = "RAW")
    private String drId1;

    @Column(name = "TERM", length = 250)
    private String term;

    @Column(name = "ERPSFD", length = 4000)
    private String erpsfd;

    @Column(name = "PURCHASE", length = 4000)
    private String purchase;

    @Column(name = "BUSINESS_UNIT", length = 20)
    private String businessUnit;

    @Column(name = "INSTANCE", length = 30)
    private String instance;

    @Column(name = "ERP_NO", length = 20)
    private String erpNo;

    @Column(name = "STATUS", length = 30)
    private String status;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

    @Column(name = "EDIT_DATE")
    private LocalDateTime editDate;

    @Column(name = "CREATE_BY", length = 50)
    private String createBy;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "LOCALE", length = 5)
    private String locale;

    @Column(name = "O_ERP_STATUS", length = 30)
    private String oErpStatus;

    @Column(name = "IMAG_IND", length = 1)
    private String imagInd;

    @Column(name = "MATL_TYPE", length = 20)
    private String matlType;

    @Column(name = "MATL_GROUP", length = 20)
    private String matlGroup;

    @Column(name = "CLASS_CONCEPT_ID", length = 30)
    private String classConceptId;

    @Column(name = "APPROVED_BY", length = 50)
    private String approvedBy;

    @Column(name = "APPROVED_DATE")
    private LocalDateTime approvedDate;

    @Column(name = "ERP_COMMENT", length = 4000)
    private String erpComment;

    @Column(name = "ERP_TRANSFERRED_DATE")
    private LocalDateTime erpTransferredDate;

    @Column(name = "ERP", length = 20)
    private String erp;

    @Column(name = "UOM", length = 10)
    private String uom;

    @Column(name = "REGION", length = 20)
    private String region;

    @Column(name = "CONCEPT_ID", length = 30)
    private String conceptId;

    @Column(name = "HIDDEN_FLAG", length = 1)
    private String hiddenFlag;

    @Column(name = "RECORD_TYPE", length = 20)
    private String recordType;

    @Column(name = "RECORD_GROUP", length = 20)
    private String recordGroup;

    @Column(name = "TRUST_LEVEL", length = 2)
    private String trustLevel;

    @Column(name = "SOURCE", length = 100)
    private String source;

    @Column(name = "EDIT_DATE_NO", length = 12)
    private String editDateNo;

    @Column(name = "CREATE_DATE_NO", length = 14)
    private String createDateNo;

    @Column(name = "APPROVED_DATE_NO", length = 16)
    private String approvedDateNo;

    @Column(name = "ERP_TRANSFERRED_DATE_NO", length = 23)
    private String erpTransferredDateNo;

    @Column(name = "RECORD_CORP_NO", length = 100)
    private String recordCorpNo;

    @Column(name = "APC_CDE", length = 10)
    private String apcCde;

    @Column(name = "SUBSTANCE_NUMBER", length = 12)
    private String substanceNumber;

    @Column(name = "CUSTOM_COLUMN1_DATE")
    private LocalDateTime customColumn1Date;

    @Column(name = "CUSTOM_COLUMN2_DATE")
    private LocalDateTime customColumn2Date;

    @Column(name = "CUSTOM_COLUMN3_DATE")
    private LocalDateTime customColumn3Date;

    @Column(name = "CUSTOM_COLUMN4_DATE")
    private LocalDateTime customColumn4Date;

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

    @Column(name = "RECORD_STATUS", length = 20)
    private String recordStatus;

    @Column(name = "PROJECT", length = 20)
    private String project;

    @Column(name = "ENTITY", length = 50)
    private String entity;

    @Column(name = "STATUS_DESCRIPTION", length = 100)
    private String statusDescription;

    @Column(name = "ERP_NO_REF", length = 20)
    private String erpNoRef;

    @Column(name = "SEQUENCE_NO", length = 11)
    private String sequenceNo;

    @Column(name = "MATERIAL_DISCIPLINE", length = 50)
    private String materialDiscipline;

    @Column(name = "CAT_CONTROLLER", length = 100)
    private String catController;

    @Column(name = "ORGNTD_PLANT", length = 20)
    private String orgntdPlant;

    @Column(name = "EXTND_PLANT", length = 20)
    private String extndPlant;

    @Column(name = "RPRT_SOURCE", length = 10)
    private String rprtSource;

    @Column(name = "DATA_SOURCE", length = 100)
    private String dataSource;

    @Column(name = "CLIENT_DEL_FLAG", length = 40)
    private String clientDelFlag;

    @Column(name = "STOFF", length = 18)
    private String stoff;

    @Column(name = "BU_LEVEL_AUDIT_ID", length = 100)
    private String buLevelAuditId;

    @Column(name = "STG_MM_SEARCH_CUST_COL16_DATE")
    private LocalDateTime stgMmSearchCustCol16Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL17_DATE")
    private LocalDateTime stgMmSearchCustCol17Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL18_DATE")
    private LocalDateTime stgMmSearchCustCol18Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL19_DATE")
    private LocalDateTime stgMmSearchCustCol19Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL20_DATE")
    private LocalDateTime stgMmSearchCustCol20Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL21_DATE")
    private LocalDateTime stgMmSearchCustCol21Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL22_DATE")
    private LocalDateTime stgMmSearchCustCol22Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL23_DATE")
    private LocalDateTime stgMmSearchCustCol23Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL24_DATE")
    private LocalDateTime stgMmSearchCustCol24Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL25_DATE")
    private LocalDateTime stgMmSearchCustCol25Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL26_DATE")
    private LocalDateTime stgMmSearchCustCol26Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL27_DATE")
    private LocalDateTime stgMmSearchCustCol27Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL28_DATE")
    private LocalDateTime stgMmSearchCustCol28Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL29_DATE")
    private LocalDateTime stgMmSearchCustCol29Date;

    @Column(name = "STG_MM_SEARCH_CUST_COL30_DATE")
    private LocalDateTime stgMmSearchCustCol30Date;
}
