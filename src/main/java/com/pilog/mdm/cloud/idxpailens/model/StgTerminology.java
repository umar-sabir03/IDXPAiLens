package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Table(name = "STG_TERMINOLOGY")
@Data
public class StgTerminology {

    @Id
    @Column(name = "AUDIT_ID", nullable = false, length = 100)
    private String auditId;

    @Column(name = "ORGN_ID", nullable = false,columnDefinition = "RAW")
    private String orgnId;

    @Column(name = "TERM", nullable = false, length = 250)
    private String term;

    @Column(name = "DEFINITION", length = 2000)
    private String definition;

    @Column(name = "ABBREVIATION", length = 4000)
    private String abbreviation;

    @Column(name = "GUIDELINES", length = 4000)
    private String guidelines;

    @Lob
    @Column(name = "CONTENT")
    private Blob content;

    @Column(name = "CONCEPT_ID", nullable = false, length = 30)
    private String conceptId;

    @Column(name = "SERVICE_CATEGORY", length = 50)
    private String serviceCategory;

    @Column(name = "SERVICE_SUB_CATEGORY", length = 100)
    private String serviceSubCategory;

    @Column(name = "DISCIPLINE_ABBR", length = 5)
    private String disciplineAbbr;

    @Column(name = "SUB_CATEGORY_ABBR", length = 5)
    private String subCategoryAbbr;

    @Column(name = "SAC_CODE", length = 6)
    private String sacCode;

    @Column(name = "SAC_DESCR", length = 500)
    private String sacDescr;

    @Column(name = "LANGUAGE_ID", nullable = false, length = 30)
    private String languageId;

    @Column(name = "LOCALE", nullable = false, length = 10)
    private String locale;

    @Column(name = "DOMAIN", nullable = false, length = 100)
    private String domain;

    @Column(name = "CNT", length = 30)
    private String cnt;

    @Column(name = "PREF_UOM1", length = 30)
    private String prefUom1;

    @Column(name = "ASTYP_DESC", length = 50)
    private String astypDesc;

    @Column(name = "ASTYP", length = 5)
    private String astyp;

    @Column(name = "RECORD_TYPE", length = 5)
    private String recordType;

    @Column(name = "LBNUM_DESC", length = 100)
    private String lbnumDesc;

    @Column(name = "LBNUM", length = 5)
    private String lbnum;

    @Column(name = "UOM", length = 30)
    private String uom;

    @Column(name = "UNSPSC_CODE", length = 8)
    private String unspscCode;

    @Column(name = "UNSPSC_DESC", length = 200)
    private String unspscDesc;

    @Column(name = "RECORD_GROUP", length = 8)
    private String recordGroup;

    @Column(name = "RECORD_GROUP_DESC", length = 200)
    private String recordGroupDesc;

    @Column(name = "STG_TRMNLGY_CUST_COL16_DATE")
    private LocalDateTime stgTrmnlgCustCol16LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL17_DATE")
    private LocalDateTime stgTrmnlgCustCol17LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL18_DATE")
    private LocalDateTime stgTrmnlgCustCol18LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL19_DATE")
    private LocalDateTime stgTrmnlgCustCol19LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL20_DATE")
    private LocalDateTime stgTrmnlgCustCol20LocalLocalDateTimeTime;

    @Column(name = "STG_TRMNLGY_CUST_COL21_DATE")
    private LocalDateTime stgTrmnlgCustCol21LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL22_DATE")
    private LocalDateTime stgTrmnlgCustCol22LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL23_DATE")
    private LocalDateTime stgTrmnlgCustCol23LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL24_DATE")
    private LocalDateTime stgTrmnlgCustCol24LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL25_DATE")
    private LocalDateTime stgTrmnlgCustCol25LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL26_DATE")
    private LocalDateTime stgTrmnlgCustCol26LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL27_DATE")
    private LocalDateTime stgTrmnlgCustCol27LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL28_DATE")
    private LocalDateTime stgTrmnlgCustCol28LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL29_DATE")
    private LocalDateTime stgTrmnlgCustCol29LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL30_DATE")
    private LocalDateTime stgTrmnlgCustCol30LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL31_DATE")
    private LocalDateTime stgTrmnlgCustCol31LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL32_DATE")
    private LocalDateTime stgTrmnlgCustCol32LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL33_DATE")
    private LocalDateTime stgTrmnlgCustCol33LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL34_DATE")
    private LocalDateTime stgTrmnlgCustCol34LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL35_DATE")
    private LocalDateTime stgTrmnlgCustCol35LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL36_DATE")
    private LocalDateTime stgTrmnlgCustCol36LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL37_DATE")
    private LocalDateTime stgTrmnlgCustCol37LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL38_DATE")
    private LocalDateTime stgTrmnlgCustCol38LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL39_DATE")
    private LocalDateTime stgTrmnlgCustCol39LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL40_DATE")
    private LocalDateTime stgTrmnlgCustCol40LocalDateTime;

    @Column(name = "STG_TRMNLGY_CUST_COL41", length = 100)
    private String stgTrmnlgCustCol41;

    @Column(name = "STG_TRMNLGY_CUST_COL42", length = 100)
    private String stgTrmnlgCustCol42;

    @Column(name = "STG_TRMNLGY_CUST_COL43", length = 100)
    private String stgTrmnlgCustCol43;

    @Column(name = "STG_TRMNLGY_CUST_COL44", length = 100)
    private String stgTrmnlgCustCol44;

    @Column(name = "STG_TRMNLGY_CUST_COL45", length = 100)
    private String stgTrmnlgCustCol45;

    @Column(name = "STG_TRMNLGY_CUST_COL46", length = 100)
    private String stgTrmnlgCustCol46;

    @Column(name = "STG_TRMNLGY_CUST_COL47", length = 100)
    private String stgTrmnlgCustCol47;

    @Column(name = "STG_TRMNLGY_CUST_COL48", length = 100)
    private String stgTrmnlgCustCol48;

    @Column(name = "STG_TRMNLGY_CUST_COL49", length = 100)
    private String stgTrmnlgCustCol49;

    @Column(name = "STG_TRMNLGY_CUST_COL50", length = 100)
    private String stgTrmnlgCustCol50;

    @Column(name = "STG_TRMNLGY_CUST_COL51", length = 100)
    private String stgTrmnlgCustCol51;

    @Column(name = "STG_TRMNLGY_CUST_COL52", length = 100)
    private String stgTrmnlgCustCol52;

    @Column(name = "STG_TRMNLGY_CUST_COL53", length = 100)
    private String stgTrmnlgCustCol53;

    @Column(name = "STG_TRMNLGY_CUST_COL54", length = 100)
    private String stgTrmnlgCustCol54;

    @Column(name = "STG_TRMNLGY_CUST_COL55", length = 100)
    private String stgTrmnlgCustCol55;

    @Column(name = "STG_TRMNLGY_CUST_COL56", length = 100)
    private String stgTrmnlgCustCol56;

    @Column(name = "STG_TRMNLGY_CUST_COL57", length = 100)
    private String stgTrmnlgCustCol57;

    @Column(name = "STG_TRMNLGY_CUST_COL58", length = 100)
    private String stgTrmnlgCustCol58;

    @Column(name = "STG_TRMNLGY_CUST_COL59", length = 100)
    private String stgTrmnlgCustCol59;

    @Column(name = "STG_TRMNLGY_CUST_COL60", length = 100)
    private String stgTrmnlgCustCol60;

    @Column(name = "STG_TRMNLGY_CUST_COL61", length = 100)
    private String stgTrmnlgCustCol61;

    @Column(name = "STG_TRMNLGY_CUST_COL62", length = 100)
    private String stgTrmnlgCustCol62;

    @Column(name = "STG_TRMNLGY_CUST_COL63", length = 100)
    private String stgTrmnlgCustCol63;

    @Column(name = "STG_TRMNLGY_CUST_COL64", length = 100)
    private String stgTrmnlgCustCol64;

    @Column(name = "STG_TRMNLGY_CUST_COL65", length = 100)
    private String stgTrmnlgCustCol65;

    @Column(name = "STG_TRMNLGY_CUST_COL66", length = 100)
    private String stgTrmnlgCustCol66;

    @Column(name = "STG_TRMNLGY_CUST_COL67", length = 100)
    private String stgTrmnlgCustCol67;

    @Column(name = "STG_TRMNLGY_CUST_COL68", length = 100)
    private String stgTrmnlgCustCol68;

    @Column(name = "STG_TRMNLGY_CUST_COL69", length = 100)
    private String stgTrmnlgCustCol69;

    @Column(name = "STG_TRMNLGY_CUST_COL70", length = 100)
    private String stgTrmnlgCustCol70;

    @Column(name = "STG_TRMNLGY_CUST_COL71", length = 100)
    private String stgTrmnlgCustCol71;

    @Column(name = "STG_TRMNLGY_CUST_COL72", length = 100)
    private String stgTrmnlgCustCol72;

    @Column(name = "STG_TRMNLGY_CUST_COL73", length = 100)
    private String stgTrmnlgCustCol73;

    @Column(name = "STG_TRMNLGY_CUST_COL74", length = 100)
    private String stgTrmnlgCustCol74;

    @Column(name = "STG_TRMNLGY_CUST_COL75", length = 100)
    private String stgTrmnlgCustCol75;

    @Column(name = "STG_TRMNLGY_CUST_COL76", length = 100)
    private String stgTrmnlgCustCol76;

    @Column(name = "STG_TRMNLGY_CUST_COL77", length = 100)
    private String stgTrmnlgCustCol77;

    @Column(name = "STG_TRMNLGY_CUST_COL78", length = 100)
    private String stgTrmnlgCustCol78;

    @Column(name = "STG_TRMNLGY_CUST_COL79", length = 100)
    private String stgTrmnlgCustCol79;

    @Column(name = "STG_TRMNLGY_CUST_COL80", length = 100)
    private String stgTrmnlgCustCol80;

    @Column(name = "STG_TRMNLGY_CUST_COL81", length = 100)
    private String stgTrmnlgCustCol81;

    @Column(name = "STG_TRMNLGY_CUST_COL82", length = 100)
    private String stgTrmnlgCustCol82;

    @Column(name = "STG_TRMNLGY_CUST_COL83", length = 100)
    private String stgTrmnlgCustCol83;

    @Column(name = "STG_TRMNLGY_CUST_COL84", length = 100)
    private String stgTrmnlgCustCol84;

    @Column(name = "STG_TRMNLGY_CUST_COL85", length = 100)
    private String stgTrmnlgCustCol85;

    @Column(name = "STG_TRMNLGY_CUST_COL86", length = 100)
    private String stgTrmnlgCustCol86;

    @Column(name = "STG_TRMNLGY_CUST_COL87", length = 100)
    private String stgTrmnlgCustCol87;

    @Column(name = "STG_TRMNLGY_CUST_COL88", length = 100)
    private String stgTrmnlgCustCol88;

    @Column(name = "STG_TRMNLGY_CUST_COL89", length = 100)
    private String stgTrmnlgCustCol89;

    @Column(name = "STG_TRMNLGY_CUST_COL90", length = 100)
    private String stgTrmnlgCustCol90;

    @Column(name = "STG_TRMNLGY_CUST_COL91", length = 100)
    private String stgTrmnlgCustCol91;

    @Column(name = "STG_TRMNLGY_CUST_COL92", length = 100)
    private String stgTrmnlgCustCol92;

    @Column(name = "STG_TRMNLGY_CUST_COL93", length = 100)
    private String stgTrmnlgCustCol93;

    @Column(name = "STG_TRMNLGY_CUST_COL94", length = 100)
    private String stgTrmnlgCustCol94;

    @Column(name = "STG_TRMNLGY_CUST_COL95", length = 100)
    private String stgTrmnlgCustCol95;

    @Column(name = "STG_TRMNLGY_CUST_COL96", length = 100)
    private String stgTrmnlgCustCol96;

    @Column(name = "STG_TRMNLGY_CUST_COL97", length = 100)
    private String stgTrmnlgCustCol97;

    @Column(name = "STG_TRMNLGY_CUST_COL98", length = 4000)
    private String stgTrmnlgCustCol98;

    @Column(name = "STG_TRMNLGY_CUST_COL99", length = 100)
    private String stgTrmnlgCustCol99;

    @Column(name = "STG_TRMNLGY_CUST_COL100", length = 100)
    private String stgTrmnlgCustCol100;

    @Column(name = "UNSPSC_SEGMENT", length = 100)
    private String unspscSegment;

    @Column(name = "UNSPSC_SEGMENT_TITLE", length = 4000)
    private String unspscSegmentTitle;

    @Column(name = "UNSPSC_FAMILY", length = 100)
    private String unspscFamily;

    @Column(name = "UNSPSC_FAMILY_TITLE", length = 4000)
    private String unspscFamilyTitle;

    @Column(name = "UNSPSC_CLASS_NAME", length = 100)
    private String unspscClassName;

    @Column(name = "UNSPSC_CLASS_TITLE", length = 4000)
    private String unspscClassTitle;

    @Column(name = "ISIC_CODE", length = 100)
    private String isicCode;

    @Column(name = "ISIC_CODE_DESC", length = 4000)
    private String isicCodeDesc;

    @Column(name = "CLASS_CATEGORY", length = 4000)
    private String classCategory;

    @Column(name = "CLASS_COMMODITY", length = 4000)
    private String classCommodity;

    @Column(name = "UNSPSC_COMMODITY", length = 100)
    private String unspscCommodity;

    @Column(name = "UNSPSC_COMMODITY_TITLE", length = 4000)
    private String unspscCommodityTitle;

    @Column(name = "CLASS_INDUSTRY", length = 4000)
    private String classIndustry;
}