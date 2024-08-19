package com.pilog.mdm.cloud.idxpailens.repository;

import com.pilog.mdm.cloud.idxpailens.model.GridRoleLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GridRoleLinkRepository extends JpaRepository<GridRoleLink, String> {

    @Query("SELECT g.gridInitParams FROM GridRoleLink g WHERE g.gridId = :gridId AND g.roleId = :roleId")
    List<String> findGridInitParamsByGridIdAndRoleId(@Param("gridId") String gridId, @Param("roleId") String roleId);
}
