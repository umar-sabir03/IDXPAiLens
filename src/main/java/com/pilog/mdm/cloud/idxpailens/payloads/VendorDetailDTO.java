package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDetailDTO {
    private String supplierName;
    private String supplierNo;
    private String country;
    private String stateCode;
    private String state;
    private String city;
    private String postalCode;
    private String address;
}
