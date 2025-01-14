package com.dist.interview.javacc.dal.mongodb.entity;

import com.dist.interview.dalapilayer.InterviewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "interview")
public class InterviewMongoEntity implements InterviewEntity {
    @Id
    private String id;
    private String criteriaId;
    private Date interviewDate;
    private String interviewerName;
    private String companyName;
    private Date createdAt;
}
