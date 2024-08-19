package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.RecordChar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordCharRepository extends JpaRepository<RecordChar, String> {
    @Modifying
    @Query("UPDATE RecordChar r SET r.propertyValue1 = :propertyValue1 WHERE r.recordNo = :recordNo AND r.propertyName = :propertyName")
    int updatePropertyValue(@Param("recordNo") String recordNo, @Param("propertyValue1") String propertyValue1, @Param("propertyName") String propertyName);
}