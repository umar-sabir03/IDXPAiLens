package com.pilog.mdm.cloud.idxpailens.controller;

import com.pilog.mdm.cloud.idxpailens.payloads.*;
import com.pilog.mdm.cloud.idxpailens.service.IIDXPAiLensService;
import com.pilog.mdm.cloud.idxpailens.utils.AppConstants;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/aiLens")
public class IDXPAiLensController {

    @Autowired
    private IIDXPAiLensService aiLensService;

    @PostMapping("/showAILensNotificationsData")
    public ResponseEntity<Map<String, String>> showAILensNotificationsData(@RequestParam(value = "selectedType", defaultValue = "Today", required = false) String selectedType) {
        Map<String, String> notificationsData = aiLensService.generateNotificationsData(selectedType);
        return ResponseEntity.ok(notificationsData);
    }

    @PostMapping("/showAITypedValueResults")
    public ResponseEntity<Map<String, Object>> showAITypedValueResults(@RequestParam(value = "aiTypedValue") String prompt,@RequestParam String role,@RequestParam String userId) {
        Map<String, Object> resultMap = new HashMap<>();
                 resultMap = aiLensService.processAiTypedValue(prompt,role,userId);
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    @PostMapping("/loadIntialButtonsData")
    public ResponseEntity<List<Map<String, String>>> loadInitialButtonsData(@RequestParam String uxIconsType,@RequestParam String orgnId) {
        List<Map<String, String>> resultMap = aiLensService.loadIntialButtonsData(uxIconsType,orgnId);
        return ResponseEntity.ok().body(resultMap);
    }

    @PostMapping("/getReferenceLinksBasedOnRefNoAndClass")
    public ResponseEntity<Map<String, Object>> getReferenceLinksBasedOnRefNoAndClass(@RequestBody RefrenceLinksDto refrenceLinkDto) {
        Map<String, Object> resultMap = aiLensService.getReferenceLinksBasedOnRefNoAndClass(refrenceLinkDto);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);

    }

    @PostMapping("/getAILensMappingObjData")
    public ResponseEntity<Map<String, Object>> getAILensMappingObjData(@RequestBody MappingRequestDto requestData) {
        MappingObjDto mappingObj = requestData.getMappingObj();
        String recordNo = requestData.getRecordNo();
        Map<String, Object> resultObj = aiLensService.getAILensMappingObjData(mappingObj, recordNo);
        return ResponseEntity.ok(resultObj);
    }

    @PostMapping("/showAILensRecentHistory")
    public ResponseEntity<List<String>> showAILensRecentHistory(@RequestParam String userName) {
        List<String> result = aiLensService.showAILensRecentHistory(userName);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/showAILensSpendInvEqSorceAnalysisData")
    public ResponseEntity<?> showAILensSpendInvEqSorceAnalysisData(
            @RequestParam String erpNo,
            @RequestParam String recordNo,
            @RequestParam String clickedName) {
        Object result = aiLensService.showAILensSpendInvEqSorceAnalysisData(erpNo, recordNo, clickedName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAnalysisResultsBasedOnType")
    public ResponseEntity<?> getAnalysisResultsBasedOnType(
            @RequestParam("analysisType") String analysisType,
            @RequestParam("erpNo") String erpNo) {
        Object response = aiLensService.getAnalysisResultsBasedOnType(analysisType, erpNo);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/showAILensAnalyticsBasedType")
    public ResponseEntity<?> showAILensAnalyticsBasedType(
            @RequestParam("analysisType") String analysisType,
            @RequestParam("erpNo") String erpNo) {
        Object response = aiLensService.showAILensAnalyticsBasedType(analysisType, erpNo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getVendorLocations")
    public ResponseEntity<List<VendorLocationDTO>> getVendorLocations(@RequestParam String material) {
        List<VendorLocationDTO>  response= aiLensService.getVendorLocations(material);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/showAIAuditAndTimeLineData")
    public ResponseEntity<?> showAIAuditAndTimeLineData(@RequestParam String auditId,@RequestParam  String viewType,@RequestParam String recordNo) {
        Object auditHistory = aiLensService.getAuditHistory(auditId, viewType, recordNo);
        return new ResponseEntity<>(auditHistory,HttpStatus.OK);
    }

    @PostMapping("/getSearchBasedAILensResults")
    public ResponseEntity<?> getSearchBasedAILensResults(@RequestBody SearchBasedResultsDTO searchBasedResultsDTO) {
        Object searchBasedAILensResults = aiLensService.getSearchBasedAILensResults(searchBasedResultsDTO);
        return new ResponseEntity<>(searchBasedAILensResults,HttpStatus.OK);
    }

    @PostMapping("/getAILensSearchedRecordInfo")
    public ResponseEntity<?> getAILensSearchedRecordInfo(@RequestBody SearchRecordInfoDTO searchRecordInfoDTO) {
        Object obj = aiLensService.getAILensSearchedRecordInfo(searchRecordInfoDTO);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PostMapping("/aiLensDetailsBasedOnDomainTabs")
    public ResponseEntity<List<Map<String, Object>>> aiLensDetailsBasedOnDomainTabs(
          @RequestBody  DomainTabsDTO domainTabsDTO) {
        List<Map<String, Object>> result = aiLensService.aiLensDetailsBasedOnDomainTabs(domainTabsDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/showAILensDomainBasedViewData")
    public ResponseEntity<List<StgMMSearchDto>> showAILensDomainBasedViewData(@RequestBody DomainBasedViewDto domainBasedView,
                                                                              @RequestParam(value="pageNumber", defaultValue= AppConstants.pageNumber,required=false )Integer pageNumber,
                                                                              @RequestParam(value="pageSize", defaultValue=AppConstants.pageSize, required=false)Integer pageSize,
                                                                              @RequestParam(value="sortBy", defaultValue=AppConstants.sortBY, required=false)String sortBy,
                                                                              @RequestParam(value="sortType" ,defaultValue=AppConstants.sortType , required=false)String sortType) {
        List<StgMMSearchDto> domainBasedViewData = aiLensService.getDomainBasedViewData(domainBasedView,pageNumber,pageSize,sortBy,sortType);
        return ResponseEntity.ok(domainBasedViewData);
    }

    @PostMapping("/getAILensContentFromDB")
    public ResponseEntity<String> getAILensContentFromDB(@RequestBody AiLensRequest request) {
        return ResponseEntity.ok(aiLensService.getAILensContentFromDB(request));
    }

    @PostMapping("/aiInsertorUpdateDatabasedOnId")
    public ResponseEntity<String> aiInsertorUpdateDatabasedOnId(
            @Parameter(description = "GridId", example = "MM_AI_MASS_DATA_PROCESS_CREATE")
            @RequestParam String tabId,

            @Parameter(
                    description = "The update parameters in JSON format",
                    example = "{\"updateIds\": \"DataMapping1,DataMapping2,Characterstics,Reference,Document,FFT\", \"excludeParams\": \"DataMapping1,DataMapping2,FFT\"}"
            )
            @RequestParam String updateParamArray,

            @Parameter(description = "The Batch ID", example = "B00000000001346")
            @RequestParam String batchId)  {
        String resultstr ="";
        try {
            resultstr = aiLensService.aiInsertorUpdateDatabasedOnId(tabId,updateParamArray,batchId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resultstr,HttpStatus.OK);
    }
    @GetMapping("/showAISearchHistory")
    public ResponseEntity<Map<String, Object>> showAISearchHistory(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam String createBy
    ) {
        Map<String, Object> response = aiLensService.getSearchHistory(category, startDate, endDate, createBy);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/detect-text")
    public ResponseEntity<Map<String, Object>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = aiLensService.detectText(file);
        return ResponseEntity.ok().body(response);
    }

}