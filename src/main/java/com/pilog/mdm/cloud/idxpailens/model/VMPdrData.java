package com.pilog.mdm.cloud.idxpailens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "V_VM_PDR_DATA")
public class VMPdrData implements Serializable {

    @Id
    @Column(name = "SUPPLIER_NO", length = 20, nullable = false)
    private String supplierNo;

    @Column(name = "SORT2", length = 20)
    private String sort2;

    @Column(name = "NAME4", length = 1000)
    private String name4;

    @Column(name = "TELF2", length = 33)
    private String telf2;

    @Column(name = "TELF1", length = 34)
    private String telf1;

    @Column(name = "SMTP_ADDR", length = 300)
    private String smtpAddr;

    @Column(name = "O_1IPANNO", length = 100)
    private String o1IpanNo;

    @Column(name = "VEN_CLASS", length = 10)
    private String venClass;

    @Column(name = "GST_NUMBER", length = 20)
    private String gstNumber;

    @Column(name = "O_1KFTIND", length = 30)
    private String o1KftInd;

    @Column(name = "VENDOR_TYPE", length = 20)
    private String vendorType;

    @Column(name = "AADHAR_CARD", length = 20)
    private String aadharCard;

    @Column(name = "STREET", length = 60)
    private String street;

    @Column(name = "STR_SUPPL1", length = 60)
    private String strSuppl1;

    @Column(name = "STR_SUPPL2", length = 60)
    private String strSuppl2;

    @Column(name = "STR_SUPPL3", length = 60)
    private String strSuppl3;

    @Column(name = "SORT1", length = 20)
    private String sort1;

    @Column(name = "AUDIT_ID", length = 100)
    private String auditId;

    @Column(name = "RECORD_NO", length = 20)
    private String recordNo;

    @Column(name = "ACCOUNT_GROUP", length = 4)
    private String accountGroup;

    @Column(name = "INSTANCE", length = 30)
    private String instance;

    @Column(name = "PLANT", length = 20)
    private String plant;

    @Column(name = "SUPPLIER_NAME", length = 40)
    private String supplierName;

    @Column(name = "COUNTRY", length = 3)
    private String country;

    @Column(name = "STATE_CODE", length = 3)
    private String stateCode;

    @Column(name = "STATE", length = 50)
    private String state;

    @Column(name = "CITY", length = 35)
    private String city;

    @Column(name = "POSTAL_CODE", length = 10)
    private String postalCode;

    @Column(name = "ADDRESS", length = 1004)
    private String address;

    @Column(name = "TAXDETAILS", length = 138)
    private String taxDetails;

    @Column(name = "CORPGRP", length = 10)
    private String corpGrp;

    @Column(name = "NAME1", length = 250)
    private String name1;

    @Column(name = "NAME2", length = 250)
    private String name2;

    @Column(name = "NAME3", length = 250)
    private String name3;
}
