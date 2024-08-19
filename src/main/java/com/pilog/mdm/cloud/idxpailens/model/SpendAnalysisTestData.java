package com.pilog.mdm.cloud.idxpailens.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "SPEND_ANALYSIS_TEST_DATA")
public class SpendAnalysisTestData {
    @Id
   @Column(name = "PURCHASING_DOCUMENT", length = 500)
    private String purchasingDocument;

    @Column(name = "PAYMENT_TERMS", length = 500)
    private String paymentTerms;

    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;

    @Column(name = "PO_YEAR", length = 10)
    private String poYear;

    @Column(name = "ITEM", length = 500)
    private String item;

    @Column(name = "SHORT_TEXT", length = 500)
    private String shortText;

    @Column(name = "MATERIAL", length = 500)
    private String material;

    @Column(name = "COMPANY_CODE", length = 500)
    private String companyCode;

    @Column(name = "PLANT", length = 500)
    private String plant;

    @Column(name = "MATERIAL_GROUP", length = 500)
    private String materialGroup;

    @Column(name = "PROFIT_CENTER", length = 500)
    private String profitCenter;

    @Column(name = "TARGET_QUANTITY", length = 500)
    private String targetQuantity;

    @Column(name = "ORDER_QUANTITY", length = 500)
    private String orderQuantity;

    @Column(name = "ORDER_UNIT", length = 500)
    private String orderUnit;

    @Column(name = "ORDER_PRICE_UNIT", length = 500)
    private String orderPriceUnit;

    @Column(name = "NET_ORDER_PRICE", length = 500)
    private String netOrderPrice;

    @Column(name = "PRICE_UNIT", length = 500)
    private String priceUnit;

    @Column(name = "NET_ORDER_VALUE", length = 500)
    private String netOrderValue;

    @Column(name = "GROSS_ORDER_VALUE", length = 500)
    private String grossOrderValue;

    @Column(name = "VALUATION_TYPE", length = 500)
    private String valuationType;

    @Column(name = "VALUATION_CATEGORY", length = 500)
    private String valuationCategory;

    @Column(name = "BASE_UNIT_OF_MEASURE", length = 500)
    private String baseUnitOfMeasure;

    @Column(name = "PURCH_DOC_CATEGORY", length = 500)
    private String purchDocCategory;

    @Column(name = "EFFECTIVE_VALUE")
    private Integer effectiveValue;

    @Column(name = "PUR_GRP", length = 100)
    private String purGrp;

    @Column(name = "VENDOR", length = 500)
    private String vendor;

    @Column(name = "VENDOR_NAME", length = 4000)
    private String vendorName;

    @Column(name = "VENDOR_ADDRESS", length = 4000)
    private String vendorAddress;

    @Column(name = "VENDOR_CITY", length = 100)
    private String vendorCity;

    @Column(name = "VENDOR_STATE", length = 100)
    private String vendorState;

    @Column(name = "VENDOR_COUNTRY", length = 100)
    private String vendorCountry;

    @Column(name = "VENDOR_PINCODE", length = 100)
    private String vendorPincode;
}
