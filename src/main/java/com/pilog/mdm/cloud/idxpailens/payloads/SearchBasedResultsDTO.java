package com.pilog.mdm.cloud.idxpailens.payloads;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class SearchBasedResultsDTO {
    private String domainName;
    private String searchTerm;
//    private String regGrdiId;
    private String orgnId;
    @ApiModelProperty(hidden = true)
    @Setter(AccessLevel.NONE)
    private String languageId="1007-1#LG-000001#1";
    private String ssDomain;
}
