package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.StgTerminology;
import com.pilog.mdm.cloud.idxpailens.payloads.StgTerminologyDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StgTerminologyRepository extends JpaRepository<StgTerminology, String> {
    List<StgTerminology> findByTermAndDomainAndLanguageIdAndOrgnId(String term, String domain, String languageId, String orgnId);
    @Query("SELECT t FROM StgTerminology t WHERE t.conceptId = :conceptId")
    List<StgTerminology> findByConceptId(@Param("conceptId") String conceptId);
    @Query("SELECT DISTINCT new com.pilog.mdm.cloud.idxpailens.payloads.StgTerminologyDTO(t.definition, t.abbreviation, t.guidelines, " +
            "t.serviceCategory, t.unspscCode, t.unspscDesc, t.content, t.recordGroup) " +
            "FROM StgTerminology t " +
            "WHERE t.conceptId = :conceptId AND t.domain = :domain AND t.languageId = :languageId AND t.orgnId = :orgnId")
    List<StgTerminologyDTO> findDistinctTerminologies(@Param("conceptId") String conceptId,
                                                      @Param("domain") String domain,
                                                      @Param("languageId") String languageId,
                                                      @Param("orgnId") String orgnId,
                                                      Pageable pageable);


    @Query(value="SELECT t FROM STG_TERMINOLOGY t WHERE t.CONCEPT_ID IN (SELECT DISTINCT s.CONCEPT_ID FROM STG_MM_SEARCH s WHERE UPPER(s.term) LIKE UPPER(:searchTerm))",nativeQuery = true)
    List<StgTerminology> findRelevantClasses(@Param("searchTerm") String searchTerm);

}