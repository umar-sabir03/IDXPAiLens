package com.pilog.mdm.cloud.idxpailens.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductResponseDto {
    @JsonProperty("property_name")
    private String propertyName;

    @JsonProperty("property_value")
    private String propertyValue;
}
