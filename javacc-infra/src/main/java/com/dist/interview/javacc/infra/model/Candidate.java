package com.dist.interview.javacc.infra.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
public class Candidate {
    private String id;
    private String interviewId;
    private String name;
    private String email;
    private String appliedPosition;
    private List<String> skills;
    private Double interviewScore;
    private Integer responseTime;
    private String feedback;
    private Double rating;
    private Status status;
    private String location;
    private Date createdAt;
}
