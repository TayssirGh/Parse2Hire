package com.dist.interview.javacc.infra.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class Criteria {
    private String id;
    private String position;
    private Double minimumScore;
    private Integer maximumResponseTime;
    private List<String> requiredSkills;
    private String criteriaDescription;
    private String syntaxTemplate;
    private List<String> candidateIds;
    private Date createdAt;
}
