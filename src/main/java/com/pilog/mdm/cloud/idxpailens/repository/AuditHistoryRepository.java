package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.AuditHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuditHistoryRepository extends JpaRepository<AuditHistory,String> {
    List<AuditHistory> findByAuditIdAndColumnNameOrderByAuditDate(String auditId, String columnName);

    @Query(value = "SELECT newValue,auditDate FROM AuditHistory WHERE auditId = :auditId AND columnName =:columnName ORDER BY auditDate ASC")
    List<Object[]> findAuditRecords(@Param("auditId") String auditId,@Param("columnName") String columnName);

    @Query("SELECT a.newValue, a.auditDate FROM AuditHistory a WHERE a.auditId = :auditId AND a.columnName = 'CREATE_BY' ORDER BY a.newValue ASC")
    List<Object[]> findCreationDetailsByAuditId(@Param("auditId") String auditId);

    @Query("SELECT a.newValue, a.auditDate FROM AuditHistory a WHERE a.auditId = :auditId AND a.columnName = 'EDIT_BY' ORDER BY a.newValue ASC")
    List<Object[]> findEditDetailsByAuditId(@Param("auditId") String auditId);

}
