package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.VMPdrData;
import com.pilog.mdm.cloud.idxpailens.payloads.VendorDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VmPdrDataRepository extends JpaRepository<VMPdrData, String> {

    @Query("SELECT new com.pilog.mdm.cloud.idxpailens.payloads.VendorDetailDTO(v.supplierName, v.supplierNo, v.country, v.stateCode, v.state, v.city, v.postalCode, v.address) " +
            "FROM VMPdrData v WHERE UPPER(v.supplierName) LIKE UPPER(:searchTerm)")
    List<VendorDetailDTO> findVendorDetails(@Param("searchTerm") String searchTerm);
}
