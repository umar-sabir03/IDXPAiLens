package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.Data;

import java.util.List;

@Data
public class SearchResponseDTO {
    private ResponseCountDTO count;
    private RelevantClassDTO relevantClasses;
    private List<StgTerminologyDTO> stgTerminologyDto;
    private List<TerminologyDTO> terminologyDTOS;
}