package com.pilog.mdm.cloud.idxpailens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Table(name = "FVM_VIE_VENDOR_SEARCH")
public class FvmVendorSearch {
        @Id
        @Column(name = "GST_NUMBER", length = 20)
        private String gstNumber;

        @Column(name = "CREATOR_NAME", length = 101)
        private String creatorName;

        @Column(name = "APPROVER_NAME", length = 101)
        private String approverName;

        @Column(name = "RE_EVALUATION_IND", length = 1)
        private String reEvaluationInd;

        @Column(name = "AADHAR_CARD", length = 20)
        private String aadharCard;

        @Column(name = "NAME3", length = 250)
        private String name3;

        @Column(name = "TELF2", length = 20)
        private String telf2;

        @Column(name = "SORT2", length = 20)
        private String sort2;

        @Column(name = "ORT01", length = 35)
        private String ort01;

        @Column(name = "SPERM", length = 1)
        private String sperms;

        @Column(name = "SPERM_CENTRAL", length = 1)
        private String spermsCentral;

        @Column(name = "SPERQ", length = 3)
        private String sperq;

        @Column(name = "SPERR", length = 1)
        private String sperr;

        @Column(name = "SPERR_CENTRAL", length = 1)
        private String sperrCentral;

        @Column(name = "ZAHLS", length = 1)
        private String zahls;

        @Column(name = "CONCAT_STRING", length = 465)
        private String concatString;

        @Column(name = "PAN_NUMBER", length = 100)
        private String panNumber;

        @Column(name = "REMARK_TAX", length = 2000)
        private String remarkTax;

        @Column(name = "GST_CODE_BASE", length = 10)
        private String gstCodeBase;

        @Column(name = "CREATED_DATE_ERP")
        private LocalDate createdDateErp;

        @Column(name = "AI_LENS_ICON", length = 4000)
        private String aiLensIcon;

        @Column(name = "AUDIT_ID", length = 100)
        private String auditId;

        @Column(name = "RECORD_NO", length = 20)
        private String recordNo;

        @Column(name = "PLANT", length = 20)
        private String plant;

        @Column(name = "TA_NAME")
        private String taName;

        @Column(name = "SUPPLIER_NO", length = 20)
        private String supplierNo;

        @Column(name = "ACCOUNT_GROUP", length = 4)
        private String accountGroup;

        @Column(name = "VENDOR_ABBR")
        private String vendorAbbr;

        @Column(name = "SUPPL_HIG_LEVEL")
        private String supplHighLevel;

        @Column(name = "VENDOR_TYPE")
        private String vendorType;

        @Column(name = "REG_NO")
        private String regNo;

        @Column(name = "VAT_NO")
        private String vatNo;

        @Column(name = "WEB_URL")
        private String webUrl;

        @Column(name = "BLOCK_STATUS")
        private String blockStatus;

        @Column(name = "BLOCK_PURCH")
        private String blockPurch;

        @Column(name = "BLOCK_POST")
        private String blockPost;

        @Column(name = "DEL_FLAG")
        private String delFlag;

        @Column(name = "O_STATUS", length = 30)
        private String oStatus;

        @Column(name = "FOREIGN_IND", length = 10)
        private String foreignInd;

        @Column(name = "PAY_METHOD", length = 10)
        private String payMethod;

        @Column(name = "O_CPL_DES")
        private String oCplDes;

        @Column(name = "ACCEPT_COMMENT", length = 4000)
        private String acceptComment;

        @Column(name = "ACCEPT_DATE")
        private LocalDate acceptDate;

        @Column(name = "SUPPLIER_NAME", length = 40)
        private String supplierName;

        @Column(name = "SEARCH_TERM1")
        private String searchTerm1;

        @Column(name = "SEARCH_TERM2")
        private String searchTerm2;

        @Column(name = "ONETIME_IND")
        private String onetimeInd;

        @Column(name = "CREATE_BY", length = 20)
        private String createBy;

        @Column(name = "CREATE_DATE")
        private LocalDate createDate;

        @Column(name = "EDIT_BY", length = 20)
        private String editBy;

        @Column(name = "EDIT_DATE")
        private LocalDate editDate;

        @Column(name = "COMPANY_CDE", length = 4)
        private String companyCde;

        @Column(name = "PURCHASE_ORG", length = 4)
        private String purchaseOrg;

        @Column(name = "O_APPR_BY", length = 20)
        private String oApprBy;

        @Column(name = "O_APPR_DATE")
        private LocalDate oApprDate;

        @Column(name = "APPR_IND", length = 10)
        private String apprInd;

        @Column(name = "GL_IND", length = 10)
        private String glInd;

        @Column(name = "TAX_IND", length = 10)
        private String taxInd;

        @Column(name = "EXT_PLANT_IND", length = 10)
        private String extPlantInd;

        @Column(name = "EXT_PURCHAGE_ORG_IND", length = 10)
        private String extPurchageOrgInd;

        @Column(name = "EXT_COMPANY_CODE_IND", length = 10)
        private String extCompanyCodeInd;

        @Column(name = "O_1KFTIND", length = 30)
        private String o1kftind;

        @Column(name = "ATTACH_IND", length = 1)
        private String attachInd;

        @Column(name = "O_1ICSTNO", length = 100)
        private String o1icstno;

        @Column(name = "O_1IPANNO", length = 100)
        private String o1ipanNo;

        @Column(name = "O_1ISERN", length = 100)
        private String o1isern;

        @Column(name = "O_1IEXCD", length = 100)
        private String o1iexcd;

        @Column(name = "O_1IEXRN", length = 100)
        private String o1iexrn;

        @Column(name = "O_1IEXRG", length = 100)
        private String o1iexrg;

        @Column(name = "O_1ISSIST", length = 100)
        private String o1issist;

        @Column(name = "O_1ILSTNO", length = 100)
        private String o1ilstno;

        @Column(name = "ADD_UPD_IND", length = 10)
        private String addUpdInd;

        @Column(name = "CRT_UPD_IND", length = 10)
        private String crtUpdInd;

        @Column(name = "ADD_UPD_DATE")
        private LocalDate addUpdDate;

        @Column(name = "CRT_UPD_DATE")
        private LocalDate crtUpdDate;

        @Column(name = "ADD_ACCEPT_COMMENT", length = 2000)
        private String addAcceptComment;

        @Column(name = "DESCRIPTION", length = 100)
        private String description;

        @Column(name = "ORGN_ID", columnDefinition = "RAW")
        private String orgnId;

        @Column(name = "SIPM_ID", length = 100)
        private String sipmId;

        @Column(name = "ANID", length = 100)
        private String anid;

        @Column(name = "SOURCE", length = 15)
        private String source;

        @Column(name = "SENDER_MAIL", length = 50)
        private String senderMail;

        @Column(name = "SITE_VISIT", length = 1)
        private String siteVisit;

        @Column(name = "STEWARD", length = 4000)
        private String steward;

        @Column(name = "DATA_SOURCE", length = 100)
        private String dataSource;

        @Column(name = "ACC_GRP_DESCR", length = 50)
        private String accGrpDescr;
}
