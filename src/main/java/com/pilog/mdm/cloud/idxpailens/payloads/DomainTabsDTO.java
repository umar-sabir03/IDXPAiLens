package com.pilog.mdm.cloud.idxpailens.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DomainTabsDTO {
    @Schema(description = "Clicked Domain Name",example="Details")
    private String domainClickedName;
    @Schema(description = "Domain Name",example = "PRODUCT")
    private String domainTabName;
    @Schema(description = "Organization Id",example = "C1F5CFB03F2E444DAE78ECCEAD80D27D")
    private String orgnId;
    @Schema(description = "Locale",example = "en_US")
    private String locale;

}
