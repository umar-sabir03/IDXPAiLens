package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.Data;
import java.util.List;
@Data
public class VendorResponseDTO {
    private ResponseCountDTO vendorCount;
    private List<VendorNameAndNoDTO> relevantVendorDetails;
    private List<VendorDetailDTO> vendorDetailDTO;
}
