package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpendAnalysisInventoryDTO {

    private String material;
    private String plant;
    private String storageLocation;
    private double quantity;
}