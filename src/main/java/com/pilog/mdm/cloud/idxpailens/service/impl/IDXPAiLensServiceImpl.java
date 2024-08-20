package com.pilog.mdm.cloud.idxpailens.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilog.mdm.cloud.idxpailens.exception.BadRequestException;
import com.pilog.mdm.cloud.idxpailens.exception.InternalServerException;
import com.pilog.mdm.cloud.idxpailens.exception.NotFoundException;
import com.pilog.mdm.cloud.idxpailens.exception.enums.ExceptionMessage;
import com.pilog.mdm.cloud.idxpailens.model.*;
import com.pilog.mdm.cloud.idxpailens.payloads.*;
import com.pilog.mdm.cloud.idxpailens.repository.*;
import com.pilog.mdm.cloud.idxpailens.service.IIDXPAiLensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.pilog.mdm.cloud.idxpailens.utils.PilogUtils.*;

@Service
public class IDXPAiLensServiceImpl implements IIDXPAiLensService {

    @Autowired
    private DalAiLensQuestionsRepository dalAiLensQuestionsRepository;
    @Autowired
    private DalAiLensHistoryRepository dalAiLensHistoryRepository;
    @Autowired
    private LensUxRepository lensUxRepository;
    @Autowired
    private BApplPropertiesRepository bApplPropertiesRepository;
    @Autowired
    private COrgnPropertiesRepository cOrgnPropertiesRepository;
    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private RecordCharMappingRepository recordCharMappingRepository;
    @Autowired
    private RecordCharRepository recordCharRepository;

    @Autowired
    private StgMMSearchRepository stgMMSearchRepository;

    @Autowired
    private StgTerminologyRepository stgTerminologyRepository;

    @Autowired
    private VVMPdtDataRepository vvmPdtDataRepository;

    @Autowired
    private AuditHistoryRepository auditHistoryRepository;

    @Autowired
    private SpendAnalysisRepository spendAnalysisRepository;
    
    @Autowired
    private ORecordBuLevelRepository oRecordBluLevelRepository;

    @Autowired
    private AiLensHistoryRepository aiLensHistoryRepository;
    @Autowired
    private FvmVendorSearchRepository fvmVendorSearchRepository;

    @Autowired
    private VmPdrDataRepository vmPdrDataRepository;

    @Autowired
    private GridRoleLinkRepository gridRoleLinkRepository;

    @Autowired
    private NativeQueriesRepository nativeQueriesRepository;

    @Override
    public Map<String, String> generateNotificationsData(String selectedType) {
        selectedType = (selectedType == null || selectedType.isEmpty()) ? "Today" : selectedType;
        int[] range = getRange(selectedType);

        int min = range[0];
        int max = range[1];

        Random rand = new Random();
        Map<String, String> resultMap = new LinkedHashMap<>();

        String result = String.format("%s %d Materials are Registered.%n" +
                        "%s %d Materials are transferred to SAP.%n" +
                        "%s %d Services are Created.%n" +
                        "%s %d Vendors are created.%n" +
                        "%s %d iSPIR Templates are created.",
                selectedType, getRandomNumber(rand, min, max),
                selectedType, getRandomNumber(rand, min, max),
                selectedType, getRandomNumber(rand, min, max),
                selectedType, getRandomNumber(rand, min, max),
                selectedType, getRandomNumber(rand, min, max));

        fillResultObject(resultMap, result);

        return resultMap;
    }

    @Override
    public Map<String, Object> processAiTypedValue(String prompt,String role,String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        if (prompt != null && !prompt.isEmpty() && !"null".equalsIgnoreCase(prompt)) {
            boolean statusCondition = prompt.contains("Status") || prompt.contains("status") || prompt.contains("STATUS");
            boolean mdrmCondition = prompt.contains("MDRM") || prompt.contains("mdrm") || prompt.contains("Mdrm");
            boolean progressCondition = prompt.contains("PROGRESS") || prompt.contains("Progress") || prompt.contains("progress");

            if ((statusCondition && mdrmCondition) || prompt.contains("iMDRM") || (progressCondition && mdrmCondition)) {
                resultMap.put("options", new String[]{"Today", "Yesterday", "Last Week", "Last Month"});
            } else {
                String result = getAITypedValueResults(prompt,role,userId);
                if (result != null && !result.isEmpty()) {
                    resultMap.put("result", result);
                } else {
                    resultMap.put("result", "No results found.");
                }
            }
        } else {
            resultMap.put("result", "Invalid prompt.");
            throw new BadRequestException(HttpStatus.BAD_REQUEST,"invalid prompt","invalid.prompt");
        }

        return resultMap;
    }

    @Override
    public List<Map<String, String>> loadIntialButtonsData(String uxIconsType,String orgnId) {
        List<Map<String, String>> resultList = new ArrayList<>();
 //       String orgnId = "C1F5CFB03F2E444DAE78ECCEAD80D27D";  // get from token
        List<LensUx> lensUxList = lensUxRepository.findByOrgnIdAndUxTypeAndUxActiveFlag(orgnId, uxIconsType, "Y");
        if (lensUxList.isEmpty()) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, ExceptionMessage.NO_DATA_FOUND.getMessage(), ExceptionMessage.NO_DATA_FOUND.getErrorCode());
        }
        for (LensUx lensUx : lensUxList) {
            Map<String, String> itemMap = new HashMap<>();
            itemMap.put("attributes", lensUx.getUxIcon());
            itemMap.put("icon", lensUx.getUxNavigationPath());
            resultList.add(itemMap);
        }
        return resultList;
    }

    @Override
    public Map<String, Object> getReferenceLinksBasedOnRefNoAndClass(RefrenceLinksDto refrenceLinkDto) {
        Map<String, Object> resultMap = new HashMap<>();
        String userid = refrenceLinkDto.getUserId();
        String ssOrgnId = refrenceLinkDto.getOrgnId();

        BApplProperties bapplPropertiesVal = bApplPropertiesRepository.findByKeyName("DATA_REF_ENRINCHMENT_URL");
        String referenceUrl = (bapplPropertiesVal != null) ? bapplPropertiesVal.getProcessValue() : "";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("Class", refrenceLinkDto.getClassName());
        formData.add("Reference_Number", refrenceLinkDto.getRefrenceNo());

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);
        RestTemplate template = new RestTemplate();

        ResponseEntity<EnrichmentResponseDto> response = template.postForEntity(referenceUrl, entity, EnrichmentResponseDto.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            EnrichmentResponseDto resp = response.getBody();
            List<ProductResponseDto> product = resp.getProduct();

            if (!product.isEmpty()) {
                DalAiLensHistory dalAiLensHistory = new DalAiLensHistory();
                dalAiLensHistory.setPromptInput(refrenceLinkDto.toString());
                dalAiLensHistory.setPromptOutput(product.toString());
                dalAiLensHistory.setCreateBy(userid);
                dalAiLensHistory.setCreateDate(new Date());
                dalAiLensHistoryRepository.save(dalAiLensHistory);
                resultMap.put("product", product);
            }
        } else {
            throw new InternalServerException("Failed to retrieve data from url.", response.getStatusCodeValue(), "retrieve.data.url.failure");
        }

        if (refrenceLinkDto.getRecordNo() != null && !refrenceLinkDto.getRecordNo().isEmpty()) {
            resultMap.put("recordNo", refrenceLinkDto.getRecordNo());
            String propertyName = "AI_LENS_REF_DOC_DUPLICATE_FLAG";
            String ssDomain = "PRODUCT";
            Pageable pageable = PageRequest.of(0, 10);
            String propertyValue = cOrgnPropertiesRepository.findPropertyValue(propertyName, ssDomain, ssOrgnId);
            if (propertyValue == null || "".equalsIgnoreCase(propertyValue)) {
                propertyValue = "N";
            }
            List<VMMRefrence> refNoList = referenceRepository.findReferencesByStripReferenceNo(refrenceLinkDto.getRefrenceNo(), pageable).getContent();

            if (!refNoList.isEmpty()) {
                List<Map<String, String>> duplicatesList = new ArrayList<>();
                for (VMMRefrence resultArr : refNoList) {
                    Map<String, String> duplicateObj = new HashMap<>();
                    duplicateObj.put("referenceNo", resultArr.getReferenceNo());
                    duplicateObj.put("recordNo", resultArr.getRecordNo());
                    duplicateObj.put("classTerm", resultArr.getClassTerm());
                    duplicateObj.put("vendorName", resultArr.getVendorName());
                    duplicatesList.add(duplicateObj);
                }
                resultMap.put("duplicates", duplicatesList);
                resultMap.put("aiLensRefDocDuplicateFlag", propertyValue);
                resultMap.put("duplicateMsg", "Duplicates are found. Do you want to continue with mapping?");
            } else {
                resultMap.put("duplicates", new ArrayList<>());
                resultMap.put("aiLensRefDocDuplicateFlag", propertyValue);
                resultMap.put("duplicateMsg", "Duplicates are not found. Do you want to continue with mapping?");
            }
        }
        return resultMap;
    }

    @Transactional
    @Override
    public Map<String, Object> getAILensMappingObjData(MappingObjDto mappingObj, String recordNo) {
        Map<String, Object> resultObj = new HashMap<>();
        try {
            List<String> matchedValList = new ArrayList<>();
            List<String> matchedColList = new ArrayList<>();
            List<RecordCharMapping> mappings = recordCharMappingRepository.findByClassTermAndAiPropertyNameAndPpoPropertyValueIsNotNull(mappingObj.getClassTerm(), mappingObj.getAiPropertyName());
 //           List<RecordCharMapping> mappings = new ArrayList<>();
            for (RecordCharMapping mapping : mappings) {
                String columnName = mapping.getAiPropertyName();
                matchedValList.add(mapping.getPpoPropertyValue());
                matchedColList.add(columnName);
            }

            int totalCnt = 0;
            if (!matchedValList.isEmpty()) {
                List<Map<String, String>> resultRows = new ArrayList<>();

                for (int j = 0; j < matchedValList.size(); j++) {
                    int updateCount = recordCharRepository.updatePropertyValue(recordNo,
                            mappingObj.getAiPropertyName().toUpperCase(),
                            matchedValList.get(j));

                    totalCnt += updateCount;

                    if (updateCount > 0) {
                        Map<String, String> row = new HashMap<>();
                        row.put("recordNo", recordNo);
                        row.put("propertyName", matchedValList.get(j));
                        row.put("propertyValue", mappingObj.getAiPropertyName());
                        resultRows.add(row);
                    }
                }

                resultObj.put("message", totalCnt + " Row(s) are Updated Successfully.");
                resultObj.put("flag", true);
                resultObj.put("rows", resultRows);
            } else {
                resultObj.put("message", "Properties are not matched.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultObj.put("flag", false);
            resultObj.put("message", "Unable to mapping.");
        }

        return resultObj;
    }

    @Override
    public List<StgMMSearchDto> getDomainBasedViewData(DomainBasedViewDto domainBasedView,Integer pageNumber,Integer pageSize,String sortBy,String sortType) {
        List<StgMMSearchDto> stgMMSearchList = new ArrayList<>();

        String orgnId = domainBasedView.getOrgnId();
        String locale = domainBasedView.getLocale();
        String domainName = domainBasedView.getDomainName();
        String status = domainBasedView.getStatus();

        Sort sort=(sortType.equalsIgnoreCase("asc"))?sort=Sort.by(sortBy).ascending(): (Sort.by(sortBy).descending());

        Pageable p=PageRequest.of(pageNumber, pageSize, sort);

        if ("PRODUCT".equalsIgnoreCase(domainName)) {
            if ("ALL".equalsIgnoreCase(status)) {
                Page<StgMMSearch> stgMMSearchPage = stgMMSearchRepository.findAllByOrgnIdAndLocale(orgnId, locale,p);
               List<StgMMSearch> stgMMSearchListData= stgMMSearchPage.getContent();
                stgMMSearchList = stgMMSearchListData.stream().map(stgMMSearch -> {
                    StgMMSearchDto stgMMSearchDto = new StgMMSearchDto();
                    stgMMSearchDto.setTerm(stgMMSearch.getTerm());
                    stgMMSearchDto.setErpNo(stgMMSearch.getErpNo());
                    stgMMSearchDto.setErpsfd(stgMMSearch.getErpsfd());
                    stgMMSearchDto.setInstance(stgMMSearch.getInstance());
                    stgMMSearchDto.setPurchase(stgMMSearch.getPurchase());
                    stgMMSearchDto.setMatlGroup(stgMMSearch.getMatlGroup());
                    stgMMSearchDto.setStatus(stgMMSearch.getStatus());
                    stgMMSearchDto.setBusinessUnit(stgMMSearch.getBusinessUnit());
                    stgMMSearchDto.setMatlType(stgMMSearch.getMatlType());
                    return stgMMSearchDto;
                }).collect(Collectors.toList());
            } else {
                stgMMSearchList = stgMMSearchRepository.findByStatusContaining(status);
            }

        } else {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "invalid domain name", "invalid.domain.name");
        }

        return stgMMSearchList;
    }

    @Override
    public Map<String, Object> detectText(MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        if (file.isEmpty()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "empty file uploaded", "empty.file.upload");
        }
        String url = "https://apimobile.intellisensesolutions.com:8443/smartIntBi/1.1.0/imgTotext";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        File fileData = convertMultiPartToFile(file);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(fileData));
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            resultMap.put("message", "Text Detected Successfully");
            String responseBody = responseEntity.getBody();
        } else {
            throw new InternalServerException("Failed to retrieve data from url.", responseEntity.getStatusCodeValue(), "retrieve.data.url.failure");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getSearchHistory(String category, String startDateStr, String endDateStr, String createBy) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> searchHistoryList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = null;
        Date endDate = null;
        if (startDateStr != null && endDateStr != null) {
            try {
                startDate = dateFormat.parse(startDateStr);
                endDate = dateFormat.parse(endDateStr);
            } catch (ParseException e) {
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "invalid date format", "invalid.date.format");
            }
        } else if(category!=null){
            Calendar cal = Calendar.getInstance();
            switch (category) {
                case "today":
                    startDate = endDate = new Date();
                    break;
                case "yesterday":
                    cal.add(Calendar.DATE, -1);
                    startDate = endDate = cal.getTime();
                    break;
                case "7-days":
                    cal.add(Calendar.DATE, -7);
                    startDate = cal.getTime();
                    endDate = new Date();
                    break;
                case "30-days":
                    cal.add(Calendar.DATE, -30);
                    startDate = cal.getTime();
                    endDate = new Date();
                    break;
                case "90-days":
                    cal.add(Calendar.DATE, -90);
                    startDate = cal.getTime();
                    endDate = new Date();
                    break;
                default:
                    startDate = null;
                    endDate = null;
                    break;
            }
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST,"Either 'category' or both 'startDate' and 'endDate' must be provided.","provide category or start date and end date");
        }

        List<Object[]> results = dalAiLensHistoryRepository.findPromptInputCount(createBy, startDate, endDate);

        if (results != null && !results.isEmpty()) {
            for (Object[] record : results) {
                String promptInput = (String) record[0];
                Long count = ((Number) record[1]).longValue();
                Map<String, Object> item = new HashMap<>();
                item.put("promptInput", promptInput);
                item.put("count", count);
                searchHistoryList.add(item);
            }
        } else {
            throw new NotFoundException(HttpStatus.BAD_REQUEST, "No data found", "no.data.found");
        }

        response.put("searchHistory", searchHistoryList);


        return response;
    }

    @Override
    public String getAILensContentFromDB(AiLensRequest request) {
        String resultStr=new String();
        String aiType = request.getAiTypeFlag();
        if ("Y".equalsIgnoreCase(aiType)) {
            request.setSsDomain(request.getAiType());
        }
        List dataStr = dalAiLensHistoryRepository.getAiLensContent(
                request.getSsOrgId(), request.getSsDomain(), request.getSsLocale(),
                request.getSsUsername(), request.getSsRole(), request.getAiQueryType(),
                request.getAiQuery(), request.getAiQueryAns(), request.getAiSubQueryFlag(),
                request.getAiSearchString(), request.getAiRecordNo(), request.getAiReqNo(),
                request.getAiInstance(), request.getAiPlant(), request.getAiCompany(),
                request.getAiPorg(), request.getAiSorg(), request.getAiDC(),
                request.getAiDivision(), request.getAiERPNo()
        );


        if (dataStr != null && !dataStr.isEmpty()) {
            if (dataStr.get(0) instanceof Clob) {
                resultStr = clobToString((Clob) dataStr.get(0));
            } else {
                resultStr = (String) dataStr.get(0);
            }
        }
        return resultStr;
    }

    @Override
    public List<Map<String, Object>> aiLensDetailsBasedOnDomainTabs(DomainTabsDTO domainTabsDTO) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        String orgnId = domainTabsDTO.getOrgnId();
        String locale = domainTabsDTO.getLocale();
        String domainTabName = domainTabsDTO.getDomainTabName();
        String domainClickedName = domainTabsDTO.getDomainClickedName();
        try {
            if ("PRODUCT".equalsIgnoreCase(domainTabName.toUpperCase())) {
                if ("Details".equalsIgnoreCase(domainClickedName)) {
                    Integer totalCount = stgMMSearchRepository.countByOrgIdAndLocale(orgnId, locale);

                    Map<String, Object> totalCountData = new HashMap<>();
                    if (totalCount != null) {
                        totalCountData.put("label", "Records are Available with Product");
                        totalCountData.put("count", totalCount);
                        dataList.add(totalCountData);
                    }

                    List<Object[]> statusList = stgMMSearchRepository.findStatusCountByOrgIdAndLocale(orgnId, locale);

                    if (statusList != null) {
                        for (Object[] statusArr : statusList) {
                            String status = (String) statusArr[0];
                            Long count = (Long) statusArr[1];

                            Map<String, Object> statusData = new HashMap<>();
                            statusData.put("label", getDescriptionForStatus(status));
                            statusData.put("count", count);
                            statusData.put("status", status);
                            dataList.add(statusData);
                        }
                    }
                }else{
                    throw new BadRequestException(HttpStatus.BAD_REQUEST,"invalid domain clicked name","invalid.domain.clicked.name");
                }
            }else{
                throw new BadRequestException(HttpStatus.BAD_REQUEST,"invalid domain tab name","invalid.domain.tab.name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public Object getAILensSearchedRecordInfo(SearchRecordInfoDTO searchRecordInfoDTO) {
        String orgnId = searchRecordInfoDTO.getOrgnId();
        String languageId = searchRecordInfoDTO.getLanguageId();
        String ssDomain = searchRecordInfoDTO.getSsDomain();
        String domainName = searchRecordInfoDTO.getDomainName();
        String searchTerm = searchRecordInfoDTO.getSearchTerm();
        String classTerm = searchRecordInfoDTO.getClassTerm();
        String searchType = searchRecordInfoDTO.getSearchType();

        if ("PRODUCT".equalsIgnoreCase(domainName)) {
            if ("INFO".equalsIgnoreCase(searchType)) {
                List<StgTerminology> definitions = stgTerminologyRepository.findByTermAndDomainAndLanguageIdAndOrgnId(
                        classTerm, ssDomain, languageId, orgnId);
                if (!definitions.isEmpty()) {
                    return definitions.stream().map(term -> {
                        Map<String, String> result = new HashMap<>();
                        result.put("term", term.getTerm());
                        result.put("definition", term.getDefinition());
                        result.put("abbreviation", term.getAbbreviation());
                        result.put("guidelines", term.getGuidelines());
                        result.put("category", term.getServiceCategory());
                        result.put("unspsc", term.getUnspscCode());
                        result.put("unspscDesc", term.getUnspscDesc());
//                        result.put("content", term.getContent().toString());
                        return result;
                    }).collect(Collectors.toList());
                }
            } else {
                String searchTypeCond = "";
                if ("ERP".equalsIgnoreCase(searchType)) {
                    searchTypeCond = "B2%";
                } else if ("INP".equalsIgnoreCase(searchType)) {
                    searchTypeCond = "A";
                }
                List<StgMMSearch> results = stgMMSearchRepository.findByTermContainingIgnoreCaseAndStatusContainingIgnoreCase(
                        searchTerm, searchTypeCond);
                List<Map<String, Object>> resultList = results.stream().map(material -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("recordNo", material.getRecordNo());
                    result.put("term", material.getTerm());
                    result.put("businessUnit", material.getBusinessUnit());
                    result.put("instance", material.getInstance());
                    result.put("erpNo", material.getErpNo());
                    result.put("status", material.getStatus());
                    result.put("editBy", material.getEditBy());
                    result.put("editDate", material.getEditDate());
                    result.put("createBy", material.getCreateBy());
                    result.put("createDate", material.getCreateDate());
                    result.put("materialType", material.getMatlType());
                    result.put("materialGroup", material.getMatlGroup());
                    return result;
                }).collect(Collectors.toList());
                if(!resultList.isEmpty()){
                    return resultList;
                }else{
                    throw new BadRequestException(HttpStatus.BAD_REQUEST,"no dta found","no.data.found");
                }
            }
        } else if ("VENDOR".equalsIgnoreCase(domainName)) {
            Pageable page=PageRequest.of(0,10);
            if ("ERP".equalsIgnoreCase(searchType)) {
                page=PageRequest.of(0,4);
            }
            List<VVMPdtData> vendors = vvmPdtDataRepository.findBySupplierNameContainingIgnoreCase(searchTerm,page);
            return vendors.stream().map(vendor -> {
                Map<String, String> result = new HashMap<>();
                result.put("recordNo", vendor.getRecordNo());
                result.put("accountGroup", vendor.getAccountGroup());
                result.put("instance", vendor.getInstance());
                result.put("plant", vendor.getPlant());
                result.put("supplierName", vendor.getSupplierName());
                result.put("supplierNo", vendor.getSupplierNo());
                result.put("country", vendor.getCountry());
                result.put("stateCode", vendor.getStateCode());
                result.put("state", vendor.getState());
                result.put("city", vendor.getCity());
                result.put("postalCode", vendor.getPostalCode());
                result.put("address", vendor.getAddress());
                return result;
            }).collect(Collectors.toList());
        }
        throw new BadRequestException(HttpStatus.BAD_REQUEST,"invalid Domain Name","invalid.domain.name");
    }


    public Object getAuditHistory(String auditId,String viewType,String recordNo) {
        List<AuditHistory> results = auditHistoryRepository.findByAuditIdAndColumnNameOrderByAuditDate(auditId,"STATUS");
       if(!results.isEmpty()) {
           if(viewType.equals("AUDIT")) {
              return results.stream().map(row -> {
                   AuditHistoryDTO dto = new AuditHistoryDTO();
                   dto.setNewValue(row.getNewValue());
                   dto.setAuditDate(row.getAuditDate());
                   dto.setAuditBy(row.getAuditBy());
                   return dto;
               }).collect(Collectors.toList());
           }else{
               Set<Map<String,String>> response=new HashSet<>();
              Map<String,String> resp=new HashMap<>();
               for(AuditHistory auditHistory:results){
                   String userImage = auditHistory.getAuditBy();
                   String imageName = "";
                   if (userImage.contains("REQ")) {
                       imageName = "aiLensReq";
                   } else if (userImage.contains("APP")) {
                       imageName = "aiLensApp";
                   } else if (userImage.contains("MGR")) {
                       imageName = "aiLensMgr";
                   } else if (userImage.contains("STD")) {
                       imageName = "aiLensStd";
                   } else {
                       imageName = "aiLensMgr";
                   }
                   resp.put("userImage",userImage);
                   resp.put("imageName",imageName);
                   response.add(resp);
               }
               return response;
           }
       }else {
           throw new BadRequestException(HttpStatus.OK,"No data found","no.data.found");
       }
    }

    @Override
    public List<VendorLocationDTO> getVendorLocations(String material) {
        List<Object[]> results = spendAnalysisRepository.findVendorLocationsByMaterial(material);
        List<VendorLocationDTO> vendorLocationList = results.stream()
                .map(result -> new VendorLocationDTO((String) result[0], (String) result[1]))
                .collect(Collectors.toList());
        if(vendorLocationList.isEmpty())
            throw new NotFoundException(HttpStatus.OK,"No data found","no.data.found");
        return vendorLocationList;
    }

    @Override
    public Object showAILensAnalyticsBasedType(String analysisType, String erpNo) {
        Object response;
        switch (analysisType) {
            case "Spend Analysis":
                response = getSpendAnalysis(erpNo);
                break;
            case "Equipment Linkage":
                response = getEquipmentLinkage(erpNo);
                break;
            case "Purchasing/Sourcing History" :
                response = getPuchaseSpendAnalysis(erpNo);
                break;
            case "Sourcing Location With Price" :
                response = getVendorSpendAnalysis(erpNo);
                break;
            case "Inventory Visibility":
                response = getInventorySpendAnalysis(erpNo);
                break;
            default:
                response= "Invalid analysis type";
        }
        return response;
    }

    @Override
    public Object getAnalysisResultsBasedOnType(String analysisType, String erpNo) {
        Object response;
        switch (analysisType) {
            case "Spend Analysis":
                response = getSpendAnalysis(erpNo);
                break;
            case "Equipment Linkage":
                response = getEquipmentLinkage(erpNo);
                break;
            case "Purchasing/Sourcing History" :
                response = getPuchaseSpendAnalysis(erpNo);
                break;
            case "Sourcing Location With Price" :
                response = getVendorSpendAnalysis(erpNo);
                break;
            case "Inventory Visibility":
                response = getInventorySpendAnalysis(erpNo);
                break;
            default:
                response= "Invalid analysis type";
        }
        return response;
    }

    private int[] getRange(String selectedType) {
        switch (selectedType.toLowerCase()) {
            case "today":
                return TODAY_RANGE;
            case "yesterday":
                return YESTERDAY_RANGE;
            case "last week":
                return LAST_WEEK_RANGE;
            case "last month":
                return LAST_MONTH_RANGE;
            default:
                return DEFAULT_RANGE;
        }
    }

    private int getRandomNumber(Random rand, int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    private void fillResultObject(Map<String, String> resultMap, String result) {
        String[] resultStrArr = result.split("\r?\n");
        for (int i = 0; i < resultStrArr.length; i++) {
            resultMap.put((i + 1) + "row", resultStrArr[i].trim());
        }
    }


    private String getAITypedValueResults(String prompt,String role,String userId) {
        String apiHubResponse = new String();
        if (prompt != null && !prompt.trim().isEmpty() && !"null".equalsIgnoreCase(prompt)) {
            String domain = getDomainByRole(role);
            String promptQuestion = dalAiLensQuestionsRepository.getPromptQuestionBasedOnTypedValue(prompt.toUpperCase(), domain);
            if (promptQuestion != null && !promptQuestion.trim().isEmpty() && !"null".equalsIgnoreCase(promptQuestion)) {
                prompt = promptQuestion;
            }
        }
        Map<String, Object> input = new HashMap<>();
        Map<String, String> requestData = new HashMap();
        requestData.put("prompt", prompt);
        input.put("apiId", "9A7A1862848844B3AEBC8E1AC8B016E9");
        input.put("inputReq", requestData);

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://idxp1.pilogcloud.com/PilogApiHub/api/apihubresponse";

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(input), String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            apiHubResponse = responseEntity.getBody();
            DalAiLensHistory dalAiLensHistory = new DalAiLensHistory();
            dalAiLensHistory.setPromptInput(prompt);
            dalAiLensHistory.setPromptOutput(apiHubResponse);
            dalAiLensHistory.setCreateBy(userId);
            dalAiLensHistory.setCreateDate(new Date());
            dalAiLensHistoryRepository.save(dalAiLensHistory);
        } else {
            throw new InternalServerException("Failed to retrieve data from url.", responseEntity.getStatusCodeValue(), "retrieve.data.url.failure");
        }

        return apiHubResponse;
    }

    private File convertMultiPartToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertedFile;
    }

    private String getDescriptionForStatus(String status) {
        switch (status) {
            case "A1-REGISTERED":
                return "Registered Records";
            case "A5-SUBMITTED":
                return "Submitted Records";
            case "A6-RETURNED":
                return "Returned Records";
            case "A9-REWORKED":
                return "Reworked Records";
            case "A0-DELETED":
                return "Deleted Records";
            case "E1-REGISTERED":
                return "Extended Records";
            case "B2-ERP ACCEPTED":
                return "ERP Accepted Records";
            case "B3-ERP REJECTED":
                return "ERP Rejected Records";
            case "S1-SUPERSEDED":
                return "Superceded Records";
            default:
                return null;
        }
    }

    private Object getInventorySpendAnalysis(String erpNo) {
      return  spendAnalysisRepository.findSpendAnalysisInventoryByMaterial(erpNo);
    }

    private Object getVendorSpendAnalysis(String erpNo) {
        return spendAnalysisRepository.findVendorDetailsByMaterial(erpNo);
    }

    private Map<String,Object> getPuchaseSpendAnalysis(String erpNo) {
        Map<String,Object> resp=new HashMap<>();
        List<SpendAnalysisPurchaseDto> purchaseSpendAnalysisByMaterial = spendAnalysisRepository.findPurchaseSpendAnalysisByMaterial(erpNo);
        long totalEffectiveValue = purchaseSpendAnalysisByMaterial.stream()
                .mapToLong(SpendAnalysisPurchaseDto::getEffectiveValue)
                .sum();
        long totalNetPriceValue = purchaseSpendAnalysisByMaterial.stream()
                .mapToLong(dto -> {
                    try {
                        return Long.parseLong(dto.getNetOrderPrice());
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .sum();
        long totalQuantityValue = purchaseSpendAnalysisByMaterial.stream()
                .mapToLong(dto->{
                    try{
                        return Long.parseLong(dto.getNetOrderPrice());
                    }catch (NumberFormatException e){
                        return 0;
                    }
                })
                .sum();
        resp.put("data",purchaseSpendAnalysisByMaterial);
        resp.put("totalEffectiveValue",totalEffectiveValue);
        resp.put("totalNetPriceValue",totalNetPriceValue);
        resp.put("totalQuantityValue",totalQuantityValue);


        return resp;
    }

    private Object getEquipmentLinkage(String erpNo) {
        return nativeQueriesRepository.findEquipmentAndPlantData(erpNo);
    }

    private  Map<String,Object> getSpendAnalysis(String erpNo) {
        Map<String,Object> resp=new HashMap<>();
        List<SpendAnalysisDTO> spendAnalysisByMaterial = spendAnalysisRepository.findSpendAnalysisByMaterial(erpNo);
        long totalEffectiveValue = spendAnalysisByMaterial.stream()
                .mapToLong(SpendAnalysisDTO::getEffectiveValue)
                .sum();
        long totalNetPriceValue = spendAnalysisByMaterial.stream()
                .mapToLong(dto -> {
                    try {
                        return (long) Double.parseDouble(dto.getNetOrderPrice());
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .sum();
        long totalQuantityValue = spendAnalysisByMaterial.stream()
                .mapToLong(dto->{
                    try{
                        return (long) Double.parseDouble(dto.getOrderQuantity());
                    }catch (NumberFormatException e){
                        return 0;
                    }
                })
                .sum();
        resp.put("data",spendAnalysisByMaterial);
        resp.put("totalEffectiveValue",totalEffectiveValue);
        resp.put("totalNetPriceValue",totalNetPriceValue);
        resp.put("totalQuantityValue",totalQuantityValue);
        return resp;
    }

    @Transactional
    public Object showAILensSpendInvEqSorceAnalysisData(String erpNo, String recordNo, String clickedName) {
            if ("SAP Details".equalsIgnoreCase(clickedName)) {
                if (erpNo != null && !erpNo.isEmpty()) {
                    boolean dataExists = spendAnalysisRepository.existsByMaterial(erpNo);
                    if (dataExists) {
                        List<SpendAnalysisInvEqDTO> spendAnalysisData = getSpendAnalysisData(erpNo);
                        return spendAnalysisData;
                    } else {
                       throw new BadRequestException(HttpStatus.NOT_FOUND,"SAP Details are not available for this Record.","sap.details.not.found");
                    }
                } else {
                    throw new BadRequestException(HttpStatus.BAD_REQUEST,"invalid erp no","invalid.erp.number");
                }
            } else if ("Details".equalsIgnoreCase(clickedName)) {
                List<AuditDetailDTO> auditDetails = getAuditDetails(recordNo);
                if (!auditDetails.isEmpty()) {
                    return auditDetails;
                } else {
                    throw new BadRequestException(HttpStatus.NOT_FOUND,"Details are not available for this Record.","details.not.available");
                }
            } else {
                throw new BadRequestException(HttpStatus.NOT_FOUND,"invalid clickedName","invalid.clickedName");
            }
    }

    private List<SpendAnalysisInvEqDTO> getSpendAnalysisData(String erpNo) {
        List<SpendAnalysisInvEqDTO> dtoList = new ArrayList<>();

        List<String> recentHistObjList = Arrays.asList(
                "Spend Analysis",
                "Purchasing/Sourcing History",
                "Sourcing Location With Price",
                "Inventory Visibility",
                "Equipment Link"
        );

        List<String> imageList = Arrays.asList(
                "spendAnalysis",
                "purchasingHist",
                "sourcingLoc",
                "InventoryVis",
                "equipmentLink"
        );

        for (int i = 0; i < recentHistObjList.size(); i++) {
            String title = recentHistObjList.get(i);
            String image = imageList.get(i);
            String onclickFunction = "getTheDataBasedOnDateOptions('" + title + "','DATA','" + erpNo + "','" + image + "')";
            String buttonLabel = "View Data";
            if ("Sourcing Location With Price".equalsIgnoreCase(title)) {
                onclickFunction = "getTheDataBasedOnDateOptions('" + title + "','MAP','" + erpNo + "','" + image + "')";
                buttonLabel = "View Map";
            }

            SpendAnalysisInvEqDTO dto = new SpendAnalysisInvEqDTO();
            dto.setTitle(title);
            dto.setImage(image);
            dto.setButtonLabel(buttonLabel);
            dto.setOnclickFunction(onclickFunction);
            dto.setAdditionalInfo("");

            dtoList.add(dto);
        }

        return dtoList;
    }

    private List<AuditDetailDTO> getAuditDetails(String recordNo) {
        List<AuditDetailDTO> auditDetailDTOList = new ArrayList<>();
        List<String> auditNo = oRecordBluLevelRepository.getAuditNo(recordNo);
        if(!auditNo.isEmpty()) {
            List<Object[]> auditRecords = auditHistoryRepository.findAuditRecords(auditNo.get(0), "STATUS");
            if (!auditRecords.isEmpty()) {
                for (Object[] record : auditRecords) {
                    String status = (String) record[0];
                    LocalDate date = (LocalDate) record[1];
                    AuditDetailDTO auditDetailDTO = getAuditDescription(status, date);
                    auditDetailDTOList.add(auditDetailDTO);
                }
            }
            List<Object[]> creationList = auditHistoryRepository.findAuditRecords(auditNo.get(0),"CREATE_BY");
            if (creationList != null && !creationList.isEmpty()) {
                for (Object[] createListArr : creationList) {
                    auditDetailDTOList.add(new AuditDetailDTO("Material Created", (LocalDate) createListArr[0], (String) createListArr[1], "aiLensMaterialImgClass"));
                }
            }
            List<Object[]> editList = auditHistoryRepository.findAuditRecords(auditNo.get(0),"EDIT_BYs");
            if (editList != null && !editList.isEmpty()) {
                for (Object[] editListArr : editList) {
                    auditDetailDTOList.add(new AuditDetailDTO("Material Edited", (LocalDate) editListArr[0], (String) editListArr[1], "aiLensMaterialImgClass"));
                }
            }
        }
        return auditDetailDTOList;
    }
      private AuditDetailDTO getAuditDescription(String status, LocalDate date) {
        if (status != null && !"".equalsIgnoreCase(status)) {
            String description = "";
            String imageClass = "";

            if (status.startsWith("A") && status.endsWith("REGISTERED")) {
                description = "Material Creation Request on " + date;
                imageClass = "aiLensMaterialImgClass";
            } else if (status.startsWith("B2") && status.endsWith("ACCEPTED")) {
                description = "ERP Request Accepted on " + date;
                imageClass = "aiLensERPImgClass";
            } else if (status.startsWith("B3") && status.endsWith("REJECTED")) {
                description = "ERP Request Rejected on " + date;
                imageClass = "aiLensERPImgClass";
            } else {
                return null;
            }

            return new AuditDetailDTO(status, date, description, imageClass);
        }
        return null;
    }

    public List<String> showAILensRecentHistory(String username) {
            List<String> recentHistObjList = aiLensHistoryRepository.findRecentPromptsByUsername(username);
            if(recentHistObjList.isEmpty()){
                throw new BadRequestException(HttpStatus.BAD_REQUEST,"no data found","no.data.found");
            }
            return recentHistObjList;
    }

    public Object getSearchBasedAILensResults(SearchBasedResultsDTO searchBasedResultsDTO) {
        VendorResponseDTO response = new VendorResponseDTO();
        ResponseCountDTO countDTO = new ResponseCountDTO();
        String orgnId = searchBasedResultsDTO.getOrgnId();
        String languageId = searchBasedResultsDTO.getLanguageId();
        String domain = searchBasedResultsDTO.getSsDomain();
        String domainName = searchBasedResultsDTO.getDomainName();
        String searchTerm = searchBasedResultsDTO.getSearchTerm();
        Pageable page = PageRequest.of(0, 2);
        String releventClsCond;

        if (searchTerm.contains(",")) {
            String[] searchTermArr = searchTerm.split(",");
            releventClsCond = "UPPER(TERM) LIKE UPPER('" + searchTermArr[0].trim() + "%')";
        } else if (searchTerm.contains(" ")) {
            String[] searchTermArr = searchTerm.split(" ");
            releventClsCond = "UPPER(TERM) LIKE UPPER('" + searchTermArr[0].trim() + "%')";
        } else {
            releventClsCond = "UPPER(TERM) LIKE UPPER('" + searchTerm + "%')";
        }
        if ("PRODUCT".equalsIgnoreCase(domainName)) {
            String searchTermUpper = searchTerm.toUpperCase();

            long totalCount = stgMMSearchRepository.countByTerm(searchTermUpper + "%");
            long inProcessCount = stgMMSearchRepository.countInProcess(searchTermUpper + "%");
            long acceptedStatusCount = stgMMSearchRepository.countAcceptedStatus(searchTermUpper + "%");

            SearchResponseDTO responseDTO = new SearchResponseDTO();
            countDTO.setTotalCount(totalCount);
            countDTO.setInProcessCount(inProcessCount);
            countDTO.setAcceptedStatusCount(acceptedStatusCount);

            responseDTO.setCount(countDTO);

            List<RelevantClassDTO> relevantClasses = stgMMSearchRepository.findRelevantClasses(searchTermUpper + "%");

            if (!relevantClasses.isEmpty()) {
                responseDTO.setRelevantClasses(relevantClasses.get(0));
                List<StgTerminologyDTO> terminologyList = stgTerminologyRepository.findDistinctTerminologies((String) relevantClasses.get(0).getConceptId(),domain,languageId,orgnId,page);
                terminologyList = terminologyList.stream().map(terminology -> {
                    try {
                        if (terminology.getContent() != null) {
                            terminology.setBase64Content(terminology.getContent());
                        }
                        return terminology;
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
                responseDTO.setStgTerminologyDto(terminologyList);
            }

            List<Object[]> results = nativeQueriesRepository.findRelevantClasses(orgnId, domain, languageId, releventClsCond);
            List<TerminologyDTO> dtos = new ArrayList<>();

            for (Object[] result : results) {
                TerminologyDTO dto = new TerminologyDTO();
                dto.setTerm((String) result[0]);
                dto.setDefinition((String) result[1]);
                dto.setConceptId((String) result[2]);
                dto.setRecordGroup((String) result[3]);
                dto.setAbbreviation((String) result[4]);
                dto.setContent((String) result[5]);
                dtos.add(dto);
            }
            responseDTO.setTerminologyDTOS(dtos);

            return responseDTO;

        }else if("VENDOR".equalsIgnoreCase(domainName)){

            String formattedSearchTerm = searchTerm.toUpperCase() + "%";

            Long totalCount = fvmVendorSearchRepository.countBySupplierName(formattedSearchTerm);
            Long inProcessCount = fvmVendorSearchRepository.countInProcessBySupplierName(formattedSearchTerm);
            Long acceptedStatusCount = fvmVendorSearchRepository.countAcceptedBySupplierName(formattedSearchTerm);


            countDTO.setTotalCount(totalCount);
            countDTO.setInProcessCount(inProcessCount);
            countDTO.setAcceptedStatusCount(acceptedStatusCount);
            List<VendorNameAndNoDTO> detailsDTO = fvmVendorSearchRepository.findRelevantVendor(formattedSearchTerm);

            List<VendorDetailDTO> vendorDetails = vmPdrDataRepository.findVendorDetails(detailsDTO.get(0).getSupplierName());
            response.setVendorCount(countDTO);
            response.setRelevantVendorDetails(detailsDTO);
            if(!vendorDetails.isEmpty())
            response.setVendorDetailDTO(vendorDetails);
            return response;
        }
        throw new BadRequestException(HttpStatus.BAD_REQUEST,"invalid domain name","invalid.domain.name");
    }

    @Override
    @Transactional
    public String aiInsertorUpdateDatabasedOnId(String tabId, String paramArrayStr, String batchId) {
        String inParamStr = "";
        Map<String, Object> resultObj = new HashMap<>();
        Map<String, Object> initParamObj = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (paramArrayStr != null && !"".equalsIgnoreCase(paramArrayStr) && !"null".equalsIgnoreCase(paramArrayStr)
                    && tabId != null && !"".equalsIgnoreCase(tabId) && !"null".equalsIgnoreCase(tabId)) {

                Map<String, Object> basicData = objectMapper.readValue(paramArrayStr, Map.class);
                String updateIds = (String) basicData.get("updateIds");
                List<String> updateIdsArr = Arrays.asList(updateIds.split(","));
                basicData.put("tabId", tabId);
                basicData.put("batchId", batchId);

                if (updateIdsArr != null && !updateIdsArr.isEmpty()) {
                    for (String titleName : updateIdsArr) {
                        List<String> dataList = getCrudQueryData(tabId + titleName);
                        int updateCount = 0;

                        if (dataList != null && !dataList.isEmpty()) {
                            for (String str : dataList) {
                            //    inParamStr =clobToString(clob);
                                inParamStr=str;
                                try {
                                    if (inParamStr != null && !"".equalsIgnoreCase(inParamStr) && !"null".equalsIgnoreCase(inParamStr)) {
                                        initParamObj = getInitParamObject(inParamStr);
                                        String insertUpdQuery = (String) initParamObj.get("uuu_insertUpdateQuery");
                                        insertUpdQuery = insertUpdQuery.replace("$$", "=");
                                        String paramColumnsStr = (String) initParamObj.get("uuu_paramColumnsStr");

                                        if (insertUpdQuery != null && !"".equalsIgnoreCase(insertUpdQuery) && !"null".equalsIgnoreCase(insertUpdQuery)
                                                && paramColumnsStr != null && !"".equalsIgnoreCase(paramColumnsStr) && !"null".equalsIgnoreCase(paramColumnsStr)) {
                                            List<String> paramColumnsList = Arrays.asList(paramColumnsStr.split(","));
                                            if (paramColumnsList != null && !paramColumnsList.isEmpty()) {
                                                Map<String, Object> updateMap = new HashMap<>();
                                                for (String paramColumnName : paramColumnsList) {
                                                    if (paramColumnName != null && !"".equalsIgnoreCase(paramColumnName) && !"null".equalsIgnoreCase(paramColumnName)) {
                                                        updateMap.put(paramColumnName, basicData.get(paramColumnName.trim()));
                                                    }
                                                }
                                                System.out.println("selectQuery:::" + insertUpdQuery);
                                                System.out.println("selectQuerymap:::" + updateMap);
                                                updateCount += nativeQueriesRepository.executeUpdateSQLNoAudit(insertUpdQuery, updateMap);
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                resultObj.put(titleName,  updateCount);
                            }
                        } else {
                            resultObj.put(titleName, 0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
             return objectMapper.writeValueAsString(resultObj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getCrudQueryData(String tabId) {
        List<String> intParamObjList = null;

        try {
            intParamObjList = gridRoleLinkRepository.findGridInitParamsByGridIdAndRoleId(tabId, tabId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return intParamObjList;
    }
}