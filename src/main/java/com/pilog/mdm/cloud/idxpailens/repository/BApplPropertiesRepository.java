package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.BApplProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BApplPropertiesRepository extends JpaRepository<BApplProperties,String> {

    BApplProperties findByKeyName(String keyName);
}
