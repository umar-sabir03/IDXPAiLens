package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(
        name = "EquipmentPlantDTOMapping",
        classes = @ConstructorResult(
                targetClass = EquipmentPlantDTO.class,
                columns = {
                        @ColumnResult(name = "equipment", type = String.class),
                        @ColumnResult(name = "plant", type = String.class),
                        @ColumnResult(name = "bom_quantity", type = Long.class)
                }
        )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentPlantDTO {
    private String equipment;
    private String plant;
    private Long bomQuantity;
}
