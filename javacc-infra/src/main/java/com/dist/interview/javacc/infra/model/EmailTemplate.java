package com.dist.interview.javacc.infra.model;

import lombok.Data;

import java.util.Date;
@Data
public class EmailTemplate {
    private String id;
    private String templateName;
    private String subject;
    private String body;
    private Date createdAt;

}
