package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.FvmVendorSearch;
import com.pilog.mdm.cloud.idxpailens.payloads.VendorNameAndNoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FvmVendorSearchRepository extends JpaRepository<FvmVendorSearch,String> {
    @Query("SELECT COUNT(v.supplierName) FROM FvmVendorSearch v WHERE UPPER(v.supplierName) LIKE UPPER(:searchTerm)")
    Long countBySupplierName(@Param("searchTerm") String searchTerm);

    @Query("SELECT COUNT(v.supplierName) FROM FvmVendorSearch v WHERE UPPER(v.supplierName) LIKE UPPER(:searchTerm) AND v.oStatus LIKE '%A%'")
    Long countInProcessBySupplierName(@Param("searchTerm") String searchTerm);

    @Query("SELECT COUNT(v.supplierName) FROM FvmVendorSearch v WHERE UPPER(v.supplierName) LIKE UPPER(:searchTerm) AND v.oStatus LIKE '%B2%'")
    Long countAcceptedBySupplierName(@Param("searchTerm") String searchTerm);

    @Query("SELECT new com.pilog.mdm.cloud.idxpailens.payloads.VendorNameAndNoDTO(v.supplierName, v.supplierNo) FROM FvmVendorSearch v WHERE UPPER(v.supplierName) LIKE UPPER(:searchTerm) ORDER BY v.supplierName")
    List<VendorNameAndNoDTO> findRelevantVendor(@Param("searchTerm") String searchTerm);
}
