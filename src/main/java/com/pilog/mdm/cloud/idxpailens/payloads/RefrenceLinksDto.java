package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefrenceLinksDto {
    private String refrenceNo;
    private String className;
    private String recordNo;
    private String userId;
    private String orgnId;

}
