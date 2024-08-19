package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.payloads.DistinctEquipmentAndPlantDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class NativeQueriesRepository {
        @PersistenceContext
        private EntityManager entityManager;

    public List<DistinctEquipmentAndPlantDto> findEquipmentAndPlantData(String material) {
        String sql = "SELECT DISTINCT EQUIPMENT, PLANT, ROUND(BOM_QUANTITY) FROM (SELECT DISTINCT  PLANT, "
                + "round(AVG(ORDER_QUANTITY))/2 AS BOM_QUANTITY FROM SPEND_ANALYSIS_TEST_DATA WHERE MATERIAL = ?  "
                + "GROUP BY PLANT) A, (SELECT DISTINCT LTRIM(BOM_NO,'0') AS EQUIPMENT FROM O_RECORD_BOM WHERE BOM_NO NOT "
                + "LIKE 'NIR%' and BOM_NO LIKE '1%' AND REGEXP_LIKE(BOM_NO, '^[0-9]+$')) B";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, material);

        List<Object[]> results = query.getResultList();
        List<DistinctEquipmentAndPlantDto> dtos = new ArrayList<>();
        for (Object[] row : results) {
            String equipment = (String) row[0];
            String plant = (String) row[1];
            Integer bomQuantity = ((Number) row[2]).intValue();

            DistinctEquipmentAndPlantDto dto = new DistinctEquipmentAndPlantDto(equipment, plant, bomQuantity);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<Object[]> findRelevantClasses(String orgnId, String domain, String languageId, String releventClsCond) {
        String queryStr = "SELECT TERM, DEFINITION, CONCEPT_ID, RECORD_GROUP, UNSPSC_CODE, ABBREVIATION, CONTENT " +
                "FROM STG_TERMINOLOGY " +
                "WHERE ORGN_ID = :orgnId " +
                "AND DOMAIN = :domain " +
                "AND LANGUAGE_ID = :languageId " +
                "AND CONCEPT_ID IN (SELECT DISTINCT CONCEPT_ID FROM ORGN_TERMINOLOGY " +
                "WHERE UPPER(CONCEPT_TYPE) = UPPER('Class') " +
                "AND LANGUAGE_ID = :languageId " +
                "AND " + releventClsCond + ") " +
                "FETCH FIRST 5 ROWS ONLY";

        Query query = entityManager.createNativeQuery(queryStr);
        query.setParameter("orgnId", orgnId);
        query.setParameter("domain", domain);
        query.setParameter("languageId", languageId);

        return query.getResultList();
    }
}

