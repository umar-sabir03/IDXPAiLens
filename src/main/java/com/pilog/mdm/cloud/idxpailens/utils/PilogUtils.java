package com.pilog.mdm.cloud.idxpailens.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class PilogUtils {
    public static final int[] TODAY_RANGE = {5, 100};
    public static final int[] YESTERDAY_RANGE = {20, 100};
    public static final int[] LAST_WEEK_RANGE = {50, 200};
    public static final int[] LAST_MONTH_RANGE = {100, 1000};
    public static final int[] DEFAULT_RANGE = {5, 100};

    private static final Map<String, String> roleDomainMap = new HashMap<>();

    static {
        roleDomainMap.put("VM", "VENDOR");
        roleDomainMap.put("MM", "PRODUCT");
        roleDomainMap.put("BP", "BUSINESS_PARTNER");
        roleDomainMap.put("SM", "SERVICE");
        roleDomainMap.put("CM", "CUSTOMER");
        roleDomainMap.put("PM", "ASSET");
        roleDomainMap.put("EM", "EMPLOYEE");
        roleDomainMap.put("CRM", "CRM");
        roleDomainMap.put("EL", "ELEAVE");
        roleDomainMap.put("SRS", "SRS");
        roleDomainMap.put("TS", "TIMESHEET");
        roleDomainMap.put("LM", "LEAVE_MASTER");
        roleDomainMap.put("IM", "INCIDENT_MASTER");
    }

    public static String getDomainByRole(String role) {
        if (role == null || role.isEmpty()) {
            return "";
        }
        return roleDomainMap.entrySet().stream()
                .filter(entry -> role.startsWith(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("");
    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static String clobToString(Clob data) {
        StringBuilder sb = new StringBuilder();
        try {
            if (data != null) {
                Reader reader = data.getCharacterStream();
                BufferedReader br = new BufferedReader(reader);
                String line;
                while (null != (line = br.readLine()))
                    sb.append(line);
                br.close();
            }
        } catch (SQLException es) {
            es.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return sb.toString();
    }
    public static Map<String,Object> getInitParamObject(String gridInitParams) {
        Map<String,Object> gridOperation = new HashMap<>();
        try {
            if (gridInitParams != null && !"".equalsIgnoreCase(gridInitParams) && !"null".equalsIgnoreCase(gridInitParams)) {
                String[] operationIconsArray = gridInitParams.split("&");
                for (int j = 0; j < operationIconsArray.length; j++) {
                    if (operationIconsArray[j] != null && !"null".equalsIgnoreCase(operationIconsArray[j])
                            && !"".equalsIgnoreCase(operationIconsArray[j])) {
                        String[] paramArray = operationIconsArray[j].split("=");
                        if (paramArray != null && paramArray.length != 0) {
                            if ("uuu_TableView".equalsIgnoreCase(paramArray[0])) {
                                gridOperation.put("tableview", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("Y")) ? "Y" : "N"));
                            } else if (paramArray[0] != null && !"".equalsIgnoreCase(paramArray[0]) && paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1])) {
                                if ("persInd".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("persInd", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_HideGraph".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("umgraph", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_HideEditExport".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("editExportFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_HideUMUpdate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("umupdate", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_RunAnalysis".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("runAnalysis", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_IsUM".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("IsUM", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_HideFormInsert".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("gridformInsert", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_Hide_UnlockUser".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("unlockUsrFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_Hide_ResetUser".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("resetUsrFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_HideInsert".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("addFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_addmultirowsFlag".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("addmultirowsFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("Y")) ? "Y" : "N"));
                                } else if ("uuu_HideUpdate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("editFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    //gridOperation.put("editIcon", "images/update_icon.png");
                                } else if ("uuu_SrsNewRegister".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("srsRegisterFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("addIcon", "images/add_icon.png");
                                } else if ("uuu_HideDelete".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("deleteFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("deleteIcon", "images/delete_icon.png");
                                } else if ("uuu_HideRefresh".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("refreshFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_HidePaging".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("pagingFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    //gridOperation.put("refreshIcon", "images/refresh_icon.png");
                                } else if ("uuu_HideImport".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("importFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
//                                    gridOperation.put("importIcon", gridObjectArray[33]);
                                } else if ("uuu_importSPIRButton".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("importSPIRButton", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_verifySPIRButton".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("verifySPIRButton", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_registerSPIRButton".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("registerSPIRButton", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_deleteSPIRButton".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("deleteSPIRButton", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_HideExport".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("exportFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
//                                    gridOperation.put("importIcon", gridObjectArray[33]);
                                } else if ("uuu_HideExportExcel".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("exportExcelFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    // gridOperation.put("exportIcon", "");
                                } else if ("uuu_HideExportCSV".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("exportCSVFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                    //gridOperation.put("exportIcon", "");
                                } else if ("uuu_HideExportPDF".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("exportPDFFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_EncEditable".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("encEditable", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("Y")) ? "Y" : "N"));
//                                    gridOperation.put("exportIcon", gridObjectArray[33]);
                                } else if ("uuu_FormView".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("formEditable", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("Y")) ? "Y" : "N"));
//                                    gridOperation.put("exportIcon", gridObjectArray[33]);
                                } else if ("uuu_OrderBy".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("orderBy", paramArray[1]);
//                                    gridOperation.put("exportIcon", gridObjectArray[33]);
                                } else if ("uuu_GroupBy".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("groupBy", paramArray[1]);
                                } else if ("uuu_OpFunName".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("opFunctionName", paramArray[1]);
                                } else if ("uuu_GridRowHeight".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("uuu_GridRowHeight", paramArray[1]);
                                } else if ("uuu_nonInstance".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("NON-INSTANCE", paramArray[1]);
                                } else if ("uuu_instance".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("INSTANCE", paramArray[1]);
                                } else if ("uuu_poExt".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("PO_EXT", paramArray[1]);
                                } else if ("uuu_ccExt".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("CC_EXT", paramArray[1]);
                                } else if ("uuu_poAndCcExt".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("PO_AND_CC_EXT", paramArray[1]);
                                } else if ("uuu_soExt".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("SO_EXT", paramArray[1]);
                                } else if ("uuu_soAndCcExt".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("SO_AND_CC_EXT", paramArray[1]);
                                } else if ("uuu_panelId".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("PANEL_ID", paramArray[1]);
                                } else if ("uuu_gridId".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("GRID_ID", paramArray[1]);
                                } else if ("uuu_ShowExtPlantGrid".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("ShowExtPlantGrid", ((paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1])) ? paramArray[1] : "N"));
                                } else if ("uuu_HideAttachForm".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("gridformAttach", ((paramArray[1] != null && "N".equalsIgnoreCase(paramArray[1])) ? "Y" : "N"));
                                } else if ("uuu_AutoGenerateColumns".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("autoGenerateColumns", ((paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1])) ? paramArray[1] : ""));
                                } else if ("uuu_importDomain".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("importDomain", ((paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1])) ? paramArray[1] : ""));
                                } else if ("uuu_HideAuditViewFlag".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("auditViewFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_HideAuditGridId".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("auditGridId", paramArray[1]);
                                } else if ("uuu_HideclauseColumns".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("clauseColumns", paramArray[1]);
                                } else if ("uuu_HideSpirAttachForm".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("uuu_HideSpirAttachForm", paramArray[1]);
                                } else if ("uuu_fillDownButton".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("fillDownButton", ((paramArray[1] != null && "Y".equalsIgnoreCase(paramArray[1])) ? "Y" : "N"));
                                } else if ("uuu_fillDownColumns".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("fillDownColumns", paramArray[1]);
                                } else if ("uuu_HideEditableFlag".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("uuu_HideEditableFlag", paramArray[1]);
                                } else if ("uuu_HideTableName".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("uuu_HideTableName", paramArray[1]);
                                } else if ("uuu_HideVariableName".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("uuu_HideVariableName", paramArray[1]);
                                } else if ("uuu_HideAttachEditableFlag".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("uuu_HideAttachEditableFlag", paramArray[1]);
                                } else if ("uuu_massParams".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massParams", paramArray[1]);
                                } else if ("uuu_massValidationId".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massValidationId", paramArray[1]);
                                } else if ("uuu_massValidate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massValidateButton", paramArray[1]);
                                } else if ("uuu_massDHProcess".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massDHProcess", paramArray[1]);
                                } else if ("uuu_massProcessData".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("bulkCreate", paramArray[1]);
                                } else if ("uuu_massDHProcName".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("dhProcName", paramArray[1]);
                                } else if ("uuu_massViewName".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massViewName", paramArray[1]);
                                } else if ("uuu_massPPRSearchButton".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massPPRSearch", paramArray[1]);
                                } else if ("uuu_massCallCopyQuery".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("copyQueryFlag", paramArray[1]);
                                } else if ("uuu_runQCTool".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("runQCToolFlag", paramArray[1]);
                                } else if ("uuu_massDuplCheckFlag".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("duplCheckFlag", paramArray[1]);
                                } else if ("uuu_ClearStagingTable".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("clearStagingTable", paramArray[1]);
                                } else if ("uuu_massCopyId".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massCopyId", paramArray[1]);
                                } else if ("uuu_masterGridInd".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massMasterGridInd", paramArray[1]);
                                } else if ("uuu_masterChngInd".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massMasterChngInd", paramArray[1]);
                                } else if ("uuu_massColumnToHide".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massColumnHide", paramArray[1]);
                                } else if ("uuu_massTableUpdate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massTableUpdate", paramArray[1]);
                                } else if ("uuu_clusterRefresh".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("clusterRefreshFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_HideOpenDoc".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("gridOpenDocument", ((paramArray[1] != null && "N".equalsIgnoreCase(paramArray[1])) ? "Y" : "N"));
                                } else if ("uuu_HideOpenDocClassName".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("className", paramArray[1]);
                                } else if ("uuu_HideOpenDocMethodName".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("methodName", paramArray[1]);
                                } else if ("uuu_populateAdminFileBrowser".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("populateAdminFileBrowser", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_gridDownloadTemplate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("downloadTemplate", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_gridCalculateStock".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("calculateStock", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_importColumnToExclude".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("uuu_excludeColumns", paramArray[1]);
                                } else if ("uuu_processMrpPlanData".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("processMrpPlanData", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_gridNoRefresh".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("gridNoRefresh", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_gridDataDHURL".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("DHUrl", paramArray[1]);
                                } else if ("uuu_genericRegisterButton".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("registerButtonFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_genericRegisterGridId".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("registerButtonId", paramArray[1]);
                                } else if ("uuu_callBapiFlag".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("callBapiFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_calculateBapiName".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("calculateBapiName", paramArray[1]);
                                } else if ("uuu_tableToUpdate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("calculateTableUpd", paramArray[1]);
                                } else if ("uuu_calculateBapiMethodName".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("calculateBapiMethodName", paramArray[1]);
                                } else if ("uuu_calculateColumnsToUpdate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("calculateColumnsToUpdate", paramArray[1]);
                                } else if ("uuu_calculateWhereCondColumns".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("calculateWhereColumns", paramArray[1]);
                                } else if ("uuu_sapFileProcessButton".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("bulkUploadFileProcessFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_HideSapImagesImport".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("bulkUploadImagesImportFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
//                                    gridOperation.put("importIcon", gridObjectArray[33]);
                                } else if ("uuu_HideSapDataImport".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("bulkUploadDataImportFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
//                                    gridOperation.put("importIcon", gridObjectArray[33]);
                                } else if ("uuu_massexcluderow".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("massexcluderow", paramArray[1]);
                                } else if ("uuu_ValidColumns".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("uuu_ValidColumns", paramArray[1]);
                                } else if ("uuu_TaxonomyNewTemplate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("taxonomyNewFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TaxonomyDrTemplate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("taxonomyDrFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TaxonomyUpdateTemplate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("taxonomyUpdateFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TaxonomyPropTemplate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("taxonomyPropertyFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TaxonomyModifyTemplate".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("taxonomyModifierFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TaxonomyHome".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("taxonomyHomeFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TaxonomyCloud".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("taxonomyCloudFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_taxonomyClassDel".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("taxonomyClsDelFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TxmnyAppProcess".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("txmnyAppFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TxmnyDridProcess".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("txmnyDridFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TxmnyDridAppProcess".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("txmnyDridAppFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_TxmnyDridStagingProcess".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("txmnyDridStagingFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_DataHarmImport".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("dataHarmFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_Downloadtemplet".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("downloadTempletFlag", ((paramArray[1] != null && paramArray[1].equalsIgnoreCase("N")) ? "Y" : "N"));
                                } else if ("uuu_imageUploadLimit".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("uuu_imageUploadLimit", paramArray[1]);
                                } else if ("uuu_filesUploadSizeInMB".equalsIgnoreCase(paramArray[0]) && (paramArray[1] != null && !"".equalsIgnoreCase(paramArray[1]))) {
                                    gridOperation.put("uuu_filesUploadSizeInMB", paramArray[1]);
                                } else if ("uuu_ModelSpecDuplicateCheckMergeFlag".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("ModelSpecDuplicateChecFlag", paramArray[1]);
                                } else if ("uuu_ModelSpecDuplicateChecButtonFlag".equalsIgnoreCase(paramArray[0])) {
                                    gridOperation.put("ModelSpecDuplicateChecButtonFlag", paramArray[1]);
                                } else {
                                    gridOperation.put(paramArray[0], paramArray[1]);
                                }
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gridOperation;
    }
}