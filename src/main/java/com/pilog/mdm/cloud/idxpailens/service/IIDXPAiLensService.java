package com.pilog.mdm.cloud.idxpailens.service;


import com.pilog.mdm.cloud.idxpailens.payloads.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IIDXPAiLensService {
    Map<String, String> generateNotificationsData(String selectedType);
    Map<String, Object> processAiTypedValue(String prompt,String role,String userId);
    List<Map<String, String>> loadIntialButtonsData(String uxIconsType,String orgnId);
    Map<String, Object> getReferenceLinksBasedOnRefNoAndClass(RefrenceLinksDto refrenceLinkDto);
    Map<String, Object> getAILensMappingObjData(MappingObjDto mappingObj, String recordNo);
    List<StgMMSearchDto> getDomainBasedViewData(DomainBasedViewDto domainBasedView,Integer pageNumber,Integer pageSize,String sortBy,String sortType);

    Map<String, Object> detectText(MultipartFile file);

    Map<String, Object> getSearchHistory(String category, String startDate, String endDate, String createBy);
    String getAILensContentFromDB(AiLensRequest request);

    List<Map<String, Object>> aiLensDetailsBasedOnDomainTabs(DomainTabsDTO domainTabsDTO);
    Object getAILensSearchedRecordInfo(SearchRecordInfoDTO searchRecordInfoDTO);
    Object getAuditHistory(String auditId,String viewType,String recordNo);

    List<VendorLocationDTO> getVendorLocations(String material);

    Object showAILensAnalyticsBasedType(String analysisType, String erpNo);

    Object getAnalysisResultsBasedOnType(String analysisType, String erpNo);

    Object showAILensSpendInvEqSorceAnalysisData(String erpNo, String recordNo, String clickedName);
    List<String> showAILensRecentHistory(String username);
    Object getSearchBasedAILensResults(SearchBasedResultsDTO searchBasedResultsDTO);

}
