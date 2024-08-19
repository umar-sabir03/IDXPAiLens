package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.Data;

@Data
public class ResponseCountDTO {
    private Long totalCount;
    private Long inProcessCount;
    private Long acceptedStatusCount;
}