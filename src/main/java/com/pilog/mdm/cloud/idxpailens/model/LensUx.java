package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DAL_LENS_UX")
@Data
public class LensUx {

    @Id
    @Column(name = "ORGN_ID",columnDefinition = "RAW(16)")
    private String orgnId;

    @Column(name = "UX_DOMAIN")
    private String uxDomain;

    @Column(name = "UX_ICON")
    private String uxIcon;

    @Column(name = "UX_NAVIGATION_PATH")
    private String uxNavigationPath;

    @Column(name = "AUDIT_ID")
    private String auditId;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "EDIT_BY")
    private String editBy;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "EDIT_DATE")
    @Temporal(TemporalType.DATE)
    private Date editDate;

    @Column(name = "UX_ACTIVE_FLAG")
    private String uxActiveFlag;

    @Column(name = "UX_TYPE")
    private String uxType;

    @Column(name = "UX_DESCRIPTION")
    private String uxDescription;

    @Column(name = "UX_SEQ_NO")
    private String uxSeqNo;

}
