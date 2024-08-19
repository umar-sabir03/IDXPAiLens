package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.Data;

import java.util.List;


@Data
public class EnrichmentResponseDto {
    private List<ProductResponseDto> product;
}