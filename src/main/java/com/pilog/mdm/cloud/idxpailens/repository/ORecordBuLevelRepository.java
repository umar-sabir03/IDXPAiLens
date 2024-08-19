package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.ORecordBuLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ORecordBuLevelRepository extends JpaRepository<ORecordBuLevel,String> {

    @Query("SELECT auditId FROM ORecordBuLevel WHERE recordNo =:recordNo")
    List<String> getAuditNo(@Param("recordNo") String recordNo);

}
