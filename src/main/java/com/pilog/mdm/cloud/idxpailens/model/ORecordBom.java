package com.pilog.mdm.cloud.idxpailens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "O_RECORD_BOM")
public class ORecordBom {

    @Id
    @Column(name = "RECORD_NO", length = 20)
    private String recordNo;

    @Column(name = "INSTANCE", length = 20)
    private String instance;

    @Column(name = "PLANT", length = 20)
    private String plant;

    @Column(name = "BOM_CAT", length = 5)
    private String bomCat;

    @Column(name = "BOM_NO", length = 200)
    private String bomNo;

    @Column(name = "BOM_USAGE", length = 5)
    private String bomUsage;

    @Column(name = "BOM_UOM", length = 5)
    private String bomUom;

    @Column(name = "BOM_QUANTITY", length = 20)
    private String bomQuantity;

    @Column(name = "BOM_ITEM_CAT", length = 5)
    private String bomItemCat;

    @Column(name = "EDIT_BY", length = 20)
    private String editBy;

    @Column(name = "EDIT_DATE")
    private LocalDate editDate;

    @Column(name = "CREATE_BY", length = 20)
    private String createBy;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

    @Column(name = "BOM_AUDIT_ID", length = 100)
    private String bomAuditId;

    @Column(name = "BOM_ERP_NO", length = 50)
    private String bomErpNo;

    @Column(name = "AVAILABLE_FLAG", length = 1)
    private String availableFlag;

    @Column(name = "BOM_MESG", length = 400)
    private String bomMesg;

    @Column(name = "BOM_CUST_COLUMN1_DATE")
    private LocalDate bomCustColumn1Date;

    @Column(name = "BOM_CUST_COLUMN2_DATE")
    private LocalDate bomCustColumn2Date;

    @Column(name = "BOM_CUST_COLUMN3_DATE")
    private LocalDate bomCustColumn3Date;

    @Column(name = "BOM_CUST_COLUMN4_DATE")
    private LocalDate bomCustColumn4Date;

    @Column(name = "BOM_CUST_COLUMN5", length = 4000)
    private String bomCustColumn5;

    @Column(name = "BOM_CUST_COLUMN6", length = 4000)
    private String bomCustColumn6;

    @Column(name = "BOM_CUST_COLUMN7", length = 4000)
    private String bomCustColumn7;

    @Column(name = "BOM_CUST_COLUMN8", length = 4000)
    private String bomCustColumn8;

    @Column(name = "BOM_CUST_COLUMN9", length = 4000)
    private String bomCustColumn9;

    @Column(name = "BOM_CUST_COLUMN10", length = 4000)
    private String bomCustColumn10;

    @Column(name = "BOM_CUST_COLUMN11", length = 4000)
    private String bomCustColumn11;

    @Column(name = "BOM_CUST_COLUMN12", length = 4000)
    private String bomCustColumn12;

    @Column(name = "BOM_CUST_COLUMN13", length = 4000)
    private String bomCustColumn13;

    @Column(name = "BOM_CUST_COLUMN14", length = 4000)
    private String bomCustColumn14;

    @Column(name = "BOM_CUST_COLUMN15", length = 4000)
    private String bomCustColumn15;

    @Column(name = "EQUIP_TAG_NO", length = 4000)
    private String equipTagNo;

    @Column(name = "EQUIP_MODEL_NO", length = 4000)
    private String equipModelNo;

    @Column(name = "EQUIP_SERIAL_NO", length = 4000)
    private String equipSerialNo;

    @Column(name = "EQUIP_MNFR", length = 4000)
    private String equipMnfr;

    @Column(name = "EQUIP_MNFR_NO", length = 4000)
    private String equipMnfrNo;

    @Column(name = "EQUIP_DESC", length = 4000)
    private String equipDesc;

    @Column(name = "EQUIP_DISCIPLINE", length = 4000)
    private String equipDiscipline;

    @Column(name = "EQUIP_CRITICALITY", length = 4000)
    private String equipCriticality;
}
