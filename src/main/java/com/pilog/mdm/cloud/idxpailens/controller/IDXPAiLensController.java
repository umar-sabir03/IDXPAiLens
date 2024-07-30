package com.pilog.mdm.cloud.idxpailens.controller;

import com.pilog.mdm.cloud.idxpailens.service.IIDXPAiLensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    public ResponseEntity<Map<String, Object>> showAITypedValueResults(@RequestParam(value = "aiTypedValue", required = false) String prompt) {
        Map<String, Object> resultMap = new HashMap<>();
                 resultMap = aiLensService.processAiTypedValue(prompt);
                return new ResponseEntity<>(resultMap, HttpStatus.OK);

        }
}