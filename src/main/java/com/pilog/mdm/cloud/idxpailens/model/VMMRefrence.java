package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "V_MM_REFERENCE")
@Data
public class VMMRefrence {

    @Id
    @Column(name = "ORGN_ID",columnDefinition = "RAW(32)")
    private String orgnId;

    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "REF_CUST_COLUMN15")
    private String refCustColumn15;

    @Column(name = "REF_CUST_COLUMN13")
    private String refCustColumn13;

    @Column(name = "REF_CUST_COLUMN14")
    private String refCustColumn14;

    @Column(name = "REF_AUDIT_ID")
    private String refAuditId;

    @Column(name = "RECORD_NO")
    private String recordNo;

    @Column(name = "ERP_NO")
    private String erpNo;

    @Column(name = "CLASS_TERM")
    private String classTerm;

    @Column(name = "REGION")
    private String region;

    @Column(name = "LOCALE")
    private String locale;

    @Column(name = "BUSINESS_UNIT")
    private String businessUnit;

    @Column(name = "INSTANCE")
    private String instance;

    @Column(name = "REFERENCE_TYPE")
    private String referenceType;

    @Column(name = "VENDOR_ID")
    private String vendorId;

    @Column(name = "VENDOR_NAME")
    private String vendorName;

    @Column(name = "BRAND_NAME")
    private String brandName;

    @Column(name = "STRIP_REFERENCE_NO")
    private String stripReferenceNo;

    @Column(name = "SUPERSEDED_REFERENCE")
    private String supercededReference;

    @Column(name = "R_STXT_FLAG")
    private String rStxtFlag;

    @Column(name = "R_LTXT_FLAG")
    private String rLtxtFlag;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "EDIT_DATE")
    private Date editDate;

    @Column(name = "EDIT_BY")
    private String editBy;

    @Column(name = "PREFERENCE_FLAG")
    private String preferenceFlag;

    @Column(name = "REF_SUPERSEDED_BY")
    private String refSupersededBy;

    @Column(name = "REF_SUPERSEDED_VENDOR_ID")
    private String refSupersededVendorId;

    @Column(name = "REF_DISCIPLINE")
    private String refDiscipline;

    @Column(name = "REF_CLASSIFICATION")
    private String refClassification;

    @Column(name = "REF_PROFILE")
    private String refProfile;

    @Column(name = "REF_CUST_COLUMN1_DATE")
    private Date refCustColumn1Date;

    @Column(name = "REF_CUST_COLUMN2_DATE")
    private Date refCustColumn2Date;

    @Column(name = "REF_CUST_COLUMN3_DATE")
    private Date refCustColumn3Date;

    @Column(name = "REF_CUST_COLUMN4_DATE")
    private Date refCustColumn4Date;

}
