package com.pilog.mdm.cloud.idxpailens.repository;


import com.pilog.mdm.cloud.idxpailens.model.RecordCharMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordCharMappingRepository extends JpaRepository<RecordCharMapping, String> {
    List<RecordCharMapping> findByClassTermAndAiPropertyNameAndPpoPropertyValueIsNotNull(String classTerm, String aiPropertyName);
}

