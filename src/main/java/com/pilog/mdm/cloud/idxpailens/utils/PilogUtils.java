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



}