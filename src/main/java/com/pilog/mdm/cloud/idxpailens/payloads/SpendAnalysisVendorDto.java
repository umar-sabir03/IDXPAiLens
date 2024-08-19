package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpendAnalysisVendorDto {

    private String vendor;
    private String vendorName;
    private String vendorAddress;
    private String vendorCity;
    private String vendorState;
    private String vendorCountry;
    private String vendorPincode;
}