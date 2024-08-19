package com.pilog.mdm.cloud.idxpailens.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DAL_AI_LENS_HISTORY")
public class AiLensHistory {

    @Id
    @Column(name = "AUDIT_ID", length = 100)
    private String auditId;

    @Column(name = "PROMPT_INPUT", length = 2000)
    private String promptInput;

    @Lob
    @Column(name = "PROMPT_OUTPUT")
    private String promptOutput;

    @Column(name = "CREATE_BY", length = 50)
    private String createBy;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

    @Column(name = "AI_LENS_CUST_COLN1", length = 100)
    private String aiLensCustColN1;

    @Column(name = "AI_LENS_CUST_COLN2", length = 100)
    private String aiLensCustColN2;

    @Column(name = "AI_LENS_CUST_COLN3", length = 100)
    private String aiLensCustColN3;

    @Column(name = "AI_LENS_CUST_COLN4", length = 100)
    private String aiLensCustColN4;

    @Column(name = "AI_LENS_CUST_COLN5", length = 100)
    private String aiLensCustColN5;

    @Column(name = "AI_LENS_CUST_COLN6", length = 100)
    private String aiLensCustColN6;

    @Column(name = "AI_LENS_CUST_COLN7", length = 100)
    private String aiLensCustColN7;

    @Column(name = "AI_LENS_CUST_COLN8", length = 100)
    private String aiLensCustColN8;

    @Column(name = "AI_LENS_CUST_COLN9", length = 100)
    private String aiLensCustColN9;

    @Column(name = "AI_LENS_CUST_COLN10", length = 100)
    private String aiLensCustColN10;
}
