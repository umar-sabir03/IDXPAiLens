package com.pilog.mdm.cloud.idxpailens.payloads;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AiLensRequest {

        @Schema(description = "Organization ID", example = "C1F5CFB03F2E444DAE78ECCEAD80D27D")
        private String ssOrgId;

        @Schema(description = "Domain", example = "LOGIN")
        private String ssDomain;

        @Schema(description = "Locale", example = "en_US")
        private String ssLocale;

        @Schema(description = "Username", example = "KIRAN_APP")
        private String ssUsername;

        @Schema(description = "User role", example = "MM_APPROVER")
        private String ssRole;

        @Schema(description = "Query type", example = "KNOWLEDGE")
        private String aiQueryType;

        @Schema(description = "Query", example = "optional")
        private String aiQuery;

        @Schema(description = "Query answer", example = "optional")
        private String aiQueryAns;

        @Schema(description = "Sub-query flag", example = "N")
        private String aiSubQueryFlag;

        @Schema(description = "Search string", example = "optional")
        private String aiSearchString;

        @Schema(description = "Record number", example = "optional")
        private String aiRecordNo;

        @Schema(description = "Request number", example = "optional")
        private String aiReqNo;

        @Schema(description = "Instance", example = "optional")
        private String aiInstance;

        @Schema(description = "Plant", example = "optional")
        private String aiPlant;

        @Schema(description = "Company", example = "optional")
        private String aiCompany;

        @Schema(description = "Purchase organization", example = "optional")
        private String aiPorg;

        @Schema(description = "Sales organization", example = "optional")
        private String aiSorg;

        @Schema(description = "Distribution center", example = "optional")
        private String aiDC;

        @Schema(description = "Division", example = "optional")
        private String aiDivision;

        @Schema(description = "ERP number", example = "null")
        private String aiERPNo;

        @Schema(description = "Type flag", example = "optional")
        private String aiTypeFlag;

        @Schema(description = "Type", example = "optional")
        private String aiType;
}
