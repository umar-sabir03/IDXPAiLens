package com.pilog.mdm.cloud.idxpailens.repository;


import com.pilog.mdm.cloud.idxpailens.model.DalAiLensHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DalAiLensHistoryRepository extends JpaRepository<DalAiLensHistory,String> {

    @Query("SELECT a.promptInput, COUNT(a.promptInput) " +
            "FROM DalAiLensHistory a " +
            "WHERE a.createBy = :createBy " +
            "AND (:startDate IS NULL OR a.createDate BETWEEN :startDate AND :endDate) " +
            "GROUP BY a.promptInput")
    List<Object[]> findPromptInputCount(@Param("createBy") String createBy,
                                        @Param("startDate") Date startDate,
                                        @Param("endDate") Date endDate);

    @Query(value = "SELECT FUNC_GET_AILENS_CONTENT(:ssOrgId, :ssDomain, :ssLocale, :ssUsername, :ssRole, :aiQueryType, " +
            ":aiQuery, :aiQueryAns, :aiSubQueryFlag, :aiSearchString, :aiRecordNo, :aiReqNo, :aiInstance, :aiPlant, " +
            ":aiCompany, :aiPorg, :aiSorg, :aiDC, :aiDivision, :aiERPNo) FROM DUAL", nativeQuery = true)
    List getAiLensContent(@Param("ssOrgId") String ssOrgId,
                                @Param("ssDomain") String ssDomain,
                                @Param("ssLocale") String ssLocale,
                                @Param("ssUsername") String ssUsername,
                                @Param("ssRole") String ssRole,
                                @Param("aiQueryType") String aiQueryType,
                                @Param("aiQuery") String aiQuery,
                                @Param("aiQueryAns") String aiQueryAns,
                                @Param("aiSubQueryFlag") String aiSubQueryFlag,
                                @Param("aiSearchString") String aiSearchString,
                                @Param("aiRecordNo") String aiRecordNo,
                                @Param("aiReqNo") String aiReqNo,
                                @Param("aiInstance") String aiInstance,
                                @Param("aiPlant") String aiPlant,
                                @Param("aiCompany") String aiCompany,
                                @Param("aiPorg") String aiPorg,
                                @Param("aiSorg") String aiSorg,
                                @Param("aiDC") String aiDC,
                                @Param("aiDivision") String aiDivision,
                                @Param("aiERPNo") String aiERPNo);

}
