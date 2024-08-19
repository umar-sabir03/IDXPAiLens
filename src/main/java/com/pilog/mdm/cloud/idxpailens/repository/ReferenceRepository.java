package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.VMMRefrence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReferenceRepository extends JpaRepository<VMMRefrence, String> {

    @Query("SELECT r FROM VMMRefrence r WHERE r.stripReferenceNo LIKE :referenceNo%")
    Page<VMMRefrence> findReferencesByStripReferenceNo(@Param("referenceNo") String referenceNo, Pageable pageable);
}

