package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.COrgnProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface COrgnPropertiesRepository extends JpaRepository<COrgnProperties,String> {
    @Query("SELECT p.propertyValue FROM COrgnProperties p WHERE p.propertyName = :propertyName AND p.domain = :domain AND p.orgnId = :orgnId")
    String findPropertyValue(@Param("propertyName") String propertyName, @Param("domain") String domain, @Param("orgnId") String orgnId);
}
