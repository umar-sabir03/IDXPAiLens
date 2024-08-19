package com.pilog.mdm.cloud.idxpailens.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StgMMSearchDto {
        private String term;
        private String erpsfd;
        private String purchase;
        private String businessUnit;
        private String instance;
        private String erpNo;
        private String status;
        private String matlType;
        private String matlGroup;
}
