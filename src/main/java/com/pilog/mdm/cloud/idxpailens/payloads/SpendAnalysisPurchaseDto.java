package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpendAnalysisPurchaseDto {
    private String purchasingDocument;
    private String poYear;
    private LocalDate createdDate;
    private String orderQuantity;
    private String netOrderPrice;
    private int effectiveValue;
    private String vendorName;

}
