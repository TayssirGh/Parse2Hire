package com.dist.interview.javacc.infra.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class Interview {
    private String id;
    private String criteriaId;
    private Date interviewDate;
    private String interviewerName;
    private String companyName;
    private Date createdAt;
}
