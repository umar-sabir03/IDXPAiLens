package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.Data;

@Data
public class DescriptionUpdateRequest {
    private String reqNumber;
    private String recordNo;
    private String businessUnit;
    private String instance;
    private String locale;

}
