package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.StgMMSearch;
import com.pilog.mdm.cloud.idxpailens.payloads.RelevantClassDTO;
import com.pilog.mdm.cloud.idxpailens.payloads.StgMMSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StgMMSearchRepository extends JpaRepository<StgMMSearch, String> {

//    @Query("SELECT new com.pilog.mdm.cloud.idxpailens.payloads.StgMMSearchDto(a.term, a.erpsfd, a.purchase, a.businessUnit, a.instance, a.erpNo, a.status, a.matlType, a.matlGroup) " +
//            "FROM StgMMSearch a WHERE a.orgnId = :orgnId AND a.locale = :locale")
    Page<StgMMSearch> findAllByOrgnIdAndLocale(@Param("orgnId") String orgnId, @Param("locale") String locale, Pageable pageable);

    @Query("SELECT new com.pilog.mdm.cloud.idxpailens.payloads.StgMMSearchDto(a.term, a.erpsfd, a.purchase, a.businessUnit, a.instance, a.erpNo, a.status, a.matlType, a.matlGroup) " +
            "FROM StgMMSearch a WHERE a.status LIKE %:status%")
    List<StgMMSearchDto> findByStatusContaining(@Param("status") String status);

    @Query("SELECT COUNT(s.term) FROM StgMMSearch s WHERE s.orgnId = :orgnId AND s.locale = :locale")
    Integer countByOrgIdAndLocale(@Param("orgnId") String orgnId, @Param("locale") String locale);

    @Query("SELECT s.status, COUNT(s) FROM StgMMSearch s WHERE s.orgnId = :orgnId AND s.locale = :locale GROUP BY s.status ORDER BY s.status ASC")
    List<Object[]> findStatusCountByOrgIdAndLocale(@Param("orgnId") String orgnId, @Param("locale") String locale);

    List<StgMMSearch> findByTermContainingIgnoreCaseAndStatusContainingIgnoreCase(String term, String status);

    @Query("SELECT COUNT(s) FROM StgMMSearch s WHERE UPPER(s.term) LIKE UPPER(:searchTerm)")
    long countByTerm(@Param("searchTerm") String searchTerm);

    @Query("SELECT COUNT(s) FROM StgMMSearch s WHERE UPPER(s.term) LIKE UPPER(:searchTerm) AND s.status LIKE '%A%'")
    long countInProcess(@Param("searchTerm") String searchTerm);

    @Query("SELECT COUNT(s) FROM StgMMSearch s WHERE UPPER(s.term) LIKE UPPER(:searchTerm) AND s.status LIKE '%B2%'")
    long countAcceptedStatus(@Param("searchTerm") String searchTerm);

    @Query("SELECT DISTINCT new com.pilog.mdm.cloud.idxpailens.payloads.RelevantClassDTO(s.term, s.conceptId) " +
            "FROM StgMMSearch s " +
            "WHERE UPPER(s.term) LIKE UPPER(:searchTerm)")
    List<RelevantClassDTO> findRelevantClasses(@Param("searchTerm") String searchTerm);
}