package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.VVMPdtData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VVMPdtDataRepository extends JpaRepository<VVMPdtData, Long> {
    List<VVMPdtData> findBySupplierNameContainingIgnoreCase(String supplierName, Pageable pageable);
}