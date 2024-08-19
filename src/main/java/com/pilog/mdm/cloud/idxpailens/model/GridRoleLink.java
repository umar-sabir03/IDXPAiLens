package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DAL_GRID_ROLE_LINK")
@Data
public class GridRoleLink {

    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "GRID_ID")
    private String gridId;

    @Column(name = "GRID_DESC")
    private String gridDesc;

    @Column(name = "EDIT_FLAG")
    private String editFlag;

    @Lob
    @Column(name = "GRID_INIT_PARAMS")
    private String gridInitParams;

    @Column(name = "NESTED_GRID_ID")
    private String nestedGridId;

    @Column(name = "NESTED_GRID_REL_ID")
    private String nestedGridRelId;

    @Column(name = "AUDIT_ID")
    private String auditId;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "EDIT_BY")
    private String editBy;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

    @Column(name = "EDIT_DATE")
    private LocalDate editDate;
}
