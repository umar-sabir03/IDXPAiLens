package com.pilog.mdm.cloud.idxpailens.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DAL_AI_LENS_QUESTIONS")
@Data
public class DalAiLensQuestions {

    @Id
    @GenericGenerator(name="gen2",strategy = "com.pilog.mdm.cloud.idxpailens.AiQuestionareIdGenerator")
    @GeneratedValue(generator = "gen2")
    @Column(name = "AUDIT_ID", length = 100)
    private String auditId;

    @Column(name = "SOURCE", length = 100)
    private String source;

    @Column(name = "DOMAIN", length = 50)
    private String domain;

    @Column(name = "QUESTION", length = 2000)
    private String question;

    @Column(name = "API_URL", length = 500)
    private String apiUrl;

    @Column(name = "API_TYPE", length = 100)
    private String apiType;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "CREATE_BY", length = 50)
    private String createBy;

    @Column(name = "EDIT_DATE")
    @Temporal(TemporalType.DATE)
    private Date editDate;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

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
