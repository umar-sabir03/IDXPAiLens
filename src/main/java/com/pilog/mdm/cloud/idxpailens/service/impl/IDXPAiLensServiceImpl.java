package com.pilog.mdm.cloud.idxpailens.service.impl;

import com.pilog.mdm.cloud.idxpailens.model.DalAiLensHistory;
import com.pilog.mdm.cloud.idxpailens.repository.DalAiLensHistoryRepository;
import com.pilog.mdm.cloud.idxpailens.repository.DalAiLensQuestionsRepository;
import com.pilog.mdm.cloud.idxpailens.service.IIDXPAiLensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.pilog.mdm.cloud.idxpailens.utils.NotificationRangeUtils.*;

@Service
public class IDXPAiLensServiceImpl implements IIDXPAiLensService {

    @Autowired
    private DalAiLensQuestionsRepository dalAiLensQuestionsRepository;

    @Autowired
    private DalAiLensHistoryRepository dalAiLensHistoryRepository;

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
    public Map<String, Object> processAiTypedValue(String prompt) {
            Map<String, Object> resultMap = new HashMap<>();
                if (prompt != null && !prompt.isEmpty() && !"null".equalsIgnoreCase(prompt)) {
                    boolean statusCondition = prompt.contains("Status") || prompt.contains("status") || prompt.contains("STATUS");
                    boolean mdrmCondition = prompt.contains("MDRM") || prompt.contains("mdrm") || prompt.contains("Mdrm");
                    boolean progressCondition = prompt.contains("PROGRESS") || prompt.contains("Progress") || prompt.contains("progress");

                    if ((statusCondition && mdrmCondition) || prompt.contains("iMDRM") || (progressCondition && mdrmCondition)) {
                        resultMap.put("options", new String[]{"Today", "Yesterday", "Last Week", "Last Month"});
                    } else {
                        String result = getAITypedValueResults(prompt);
                        if (result != null && !result.isEmpty()) {
                            resultMap.put("result", result);
                        } else {
                            resultMap.put("result", "No results found.");
                        }
                    }
                } else {
                    resultMap.put("result", "Invalid prompt.");
                }

            return resultMap;
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
        String[] resultStrArr = result.split("\n");
        for (int i = 0; i < resultStrArr.length; i++) {
            resultMap.put((i + 1) + "row", resultStrArr[i]);
        }
    }
    private String getAITypedValueResults(String prompt) {
    String apiHubResponse=new String();
    String role="";  // get it from token
        String userid="";
            if (prompt != null && !prompt.trim().isEmpty() && !"null".equalsIgnoreCase(prompt)) {
                String domain = getDomainByRole(role);
                String promptQuestion = dalAiLensQuestionsRepository.getPromptQuestionBasedOnTypedValue(prompt.toUpperCase(),domain);
                if (promptQuestion != null && !promptQuestion.trim().isEmpty() && !"null".equalsIgnoreCase(promptQuestion)) {
                    prompt = promptQuestion;
                }
            }
            Map<String,Object> input = new HashMap<>();
            Map<String,String> requestData = new HashMap();
            requestData.put("prompt", prompt);
            input.put("apiId", "9A7A1862848844B3AEBC8E1AC8B016E9");
            input.put("inputReq", requestData);

            RestTemplate restTemplate = new RestTemplate();
            String url = "https://idxp1.pilogcloud.com/PilogApiHub/api/apihubresponse";

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(input), String.class);

        HttpStatus statusCode = responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            apiHubResponse = responseEntity.getBody();
            DalAiLensHistory dalAiLensHistory=new DalAiLensHistory();
            dalAiLensHistory.setPromptInput(prompt);
            dalAiLensHistory.setPromptOutput(apiHubResponse);
            dalAiLensHistory.setCreateBy(userid);
            dalAiLensHistory.setCreateDate(new Date());
            dalAiLensHistoryRepository.save(dalAiLensHistory);
            }

        return apiHubResponse;
    }
}