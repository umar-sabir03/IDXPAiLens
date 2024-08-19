package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.SpendAnalysisTestData;
import com.pilog.mdm.cloud.idxpailens.payloads.SpendAnalysisDTO;
import com.pilog.mdm.cloud.idxpailens.payloads.SpendAnalysisInventoryDTO;
import com.pilog.mdm.cloud.idxpailens.payloads.SpendAnalysisPurchaseDto;
import com.pilog.mdm.cloud.idxpailens.payloads.SpendAnalysisVendorDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpendAnalysisRepository extends CrudRepository<SpendAnalysisTestData, Long> {

//    @Query("SELECT new com.pilog.mdm.cloud.idxpailens.payloads.SpendAnalysisDTO(" +
//            "sa.poYear, sa.vendorName, SUM(sa.effectiveValue), SUM(sa.orderQuantity), sa.plant, SUM(sa.netOrderPrice)) " +
//            "FROM SpendAnalysisTestData sa WHERE sa.material = :erpNo " +
//            "GROUP BY sa.poYear, sa.vendorName, sa.plant ORDER BY sa.poYear, sa.vendorName, sa.plant")
@Query("SELECT new com.pilog.mdm.cloud.idxpailens.payloads.SpendAnalysisDTO(" +
        "sa.purchasingDocument, sa.poYear, sa.item, sa.plant, " +
        "SUM(sa.orderQuantity), sa.orderUnit, SUM(sa.netOrderPrice), SUM(sa.effectiveValue)) " +
        "FROM SpendAnalysisTestData sa " +
        "WHERE sa.material = :erpNo " +
        "GROUP BY sa.purchasingDocument, sa.poYear, sa.item, sa.plant, sa.orderUnit " +
        "ORDER BY sa.poYear, sa.purchasingDocument, sa.plant")
List<SpendAnalysisDTO> findSpendAnalysisByMaterial(@Param("erpNo") String erpNo);

//    @Query(value = "SELECT DISTINCT EQUIPMENT, PLANT, ROUND(BOM_QUANTITY) FROM (SELECT DISTINCT  PLANT, "
//            + "round(AVG(ORDER_QUANTITY))/2 AS BOM_QUANTITY FROM SPEND_ANALYSIS_TEST_DATA WHERE MATERIAL= :erpNo "
//            + "GROUP BY PLANT) A, (SELECT DISTINCT LTRIM(BOM_NO,'0') AS EQUIPMENT FROM O_RECORD_BOM WHERE BOM_NO NOT "
//            + "LIKE 'NIR%' and BOM_NO LIKE '1%' AND REGEXP_LIKE(BOM_NO, '^[0-9]+$')) B",
//            nativeQuery = true)
//    List<EquipmentPlantDTO> findDistinctEquipmentsAndPlants(@Param("erpNo") String erpNo);

    @Query("SELECT new com.pilog.mdm.cloud.idxpailens.payloads.SpendAnalysisPurchaseDto(s.purchasingDocument, s.poYear, s.createdDate, " +
            "ROUND(s.orderQuantity), ROUND(s.netOrderPrice), ROUND(s.effectiveValue), s.vendorName) " +
            "FROM SpendAnalysisTestData s WHERE s.material = :material")
    List<SpendAnalysisPurchaseDto> findPurchaseSpendAnalysisByMaterial(@Param("material") String material);

    @Query("SELECT new com.pilog.mdm.cloud.idxpailens.payloads.SpendAnalysisInventoryDTO(s.material, s.plant, s.plant, ROUND(AVG(s.orderQuantity))) " +
            "FROM SpendAnalysisTestData s WHERE s.material = :material " +
            "GROUP BY s.material, s.plant")
    List<SpendAnalysisInventoryDTO> findSpendAnalysisInventoryByMaterial(@Param("material") String material);

    @Query("SELECT new com.pilog.mdm.cloud.idxpailens.payloads.SpendAnalysisVendorDto(s.vendor, s.vendorName, s.vendorAddress, " +
            "s.vendorCity, s.vendorState, s.vendorCountry, s.vendorPincode) " +
            "FROM SpendAnalysisTestData s WHERE s.material = :material AND s.vendorName IS NOT NULL")
    List<SpendAnalysisVendorDto> findVendorDetailsByMaterial(@Param("material") String material);

    @Query("SELECT v.vendorName, v.vendorAddress FROM SpendAnalysisTestData v WHERE v.material = :material")
    List<Object[]> findVendorLocationsByMaterial(@Param("material") String material);

    boolean existsByMaterial(String material);
}