package com.pilog.mdm.cloud.idxpailens.service;


import java.util.Map;

public interface IIDXPAiLensService {
    Map<String, String> generateNotificationsData(String selectedType);
    Map<String, Object> processAiTypedValue(String prompt);
}
