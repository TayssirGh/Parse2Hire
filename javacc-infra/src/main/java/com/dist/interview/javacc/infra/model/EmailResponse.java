package com.dist.interview.javacc.infra.model;

import lombok.Data;

import java.util.Date;
@Data
public class EmailResponse {
    private String id;
    private String candidateId;
    private String templateId;
    private String interviewId;
    private String to;
    private String subject;
    private String body;
    private String status;
    private Date sentAt;
}
