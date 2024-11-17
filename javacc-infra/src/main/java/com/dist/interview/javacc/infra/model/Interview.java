package com.dist.interview.javacc.infra.model;

import lombok.Data;

import java.util.Date;
@Data
public class Interview {
    private String id;
    private String candidateId;
    private String criteriaId;
    private Date interviewDate;
    private String interviewerName;
    private String companyName;
    private Double interviewScore;
    private Integer responseTime;
    private String feedback;
    private Double rating;
    private String result;
    private Date createdAt;
}
