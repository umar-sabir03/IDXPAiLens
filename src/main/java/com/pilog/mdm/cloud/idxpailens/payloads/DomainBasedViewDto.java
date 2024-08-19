package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.Data;

@Data
public class DomainBasedViewDto {
    private String status;
    private String domainName;
    private String orgnId;
    private String locale;

}
