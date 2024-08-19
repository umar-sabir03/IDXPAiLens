package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpendAnalysisDTO {

    private String purchasingDocument;
    private String poYear;
    private String item;
    private String plant;
    private String orderQuantity;
    private String orderUnit;
    private String netOrderPrice;
    private long effectiveValue;

}
