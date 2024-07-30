package com.pilog.mdm.cloud.idxpailens.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DAL_AI_LENS_HISTORY")
@Data
public class DalAiLensHistory {

    @Id
    @GenericGenerator(name = "gen1", strategy = "com.pilog.mdm.cloud.idxpailens.AiHistoryIdGenerator")
    @GeneratedValue(generator = "gen1")
    @Column(name = "AUDIT_ID")
    private String auditId;

    @Column(name = "PROMPT_INPUT", length = 2000)
    private String promptInput;

    @Lob
    @Column(name = "PROMPT_OUTPUT")
    private String promptOutput;

    @Column(name = "CREATE_BY", length = 50)
    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "AI_LENS_CUST_COLN1", length = 100)
    private String aiLensCustColn1;

    @Column(name = "AI_LENS_CUST_COLN2", length = 100)
    private String aiLensCustColn2;

    @Column(name = "AI_LENS_CUST_COLN3", length = 100)
    private String aiLensCustColn3;

    @Column(name = "AI_LENS_CUST_COLN4", length = 100)
    private String aiLensCustColn4;

    @Column(name = "AI_LENS_CUST_COLN5", length = 100)
    private String aiLensCustColn5;

    @Column(name = "AI_LENS_CUST_COLN6", length = 100)
    private String aiLensCustColn6;

    @Column(name = "AI_LENS_CUST_COLN7", length = 100)
    private String aiLensCustColn7;

    @Column(name = "AI_LENS_CUST_COLN8", length = 100)
    private String aiLensCustColn8;

    @Column(name = "AI_LENS_CUST_COLN9", length = 100)
    private String aiLensCustColn9;

    @Column(name = "AI_LENS_CUST_COLN10", length = 100)
    private String aiLensCustColn10;

}