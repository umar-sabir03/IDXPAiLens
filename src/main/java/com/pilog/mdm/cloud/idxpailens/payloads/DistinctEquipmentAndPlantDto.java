package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistinctEquipmentAndPlantDto {
        private String equipment;
        private String plant;
        private Integer bomQuantity;
}
