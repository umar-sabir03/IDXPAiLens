package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.Data;

@Data
public class TerminologyDTO {
    private String term;
    private String definition;
    private String conceptId;
    private String recordGroup;
    private String abbreviation;
    private String content;
}
