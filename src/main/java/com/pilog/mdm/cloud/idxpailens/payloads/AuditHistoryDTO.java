package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuditHistoryDTO {
    private String newValue;
    private LocalDate auditDate;
    private String auditBy;
}
