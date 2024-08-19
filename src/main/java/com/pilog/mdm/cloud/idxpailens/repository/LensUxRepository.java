package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.LensUx;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LensUxRepository extends JpaRepository<LensUx,String> {
    List<LensUx> findByOrgnIdAndUxTypeAndUxActiveFlag(String orgnId, String uxType, String uxActiveFlag);
}
