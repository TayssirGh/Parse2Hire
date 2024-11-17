package com.dist.interview.javacc.dal.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "candidate")
public class CandidateCollection {
    @Id
    private String id;
    private String interviewId;
    private String name;
    private String email;
    private String appliedPosition;
    private List<String> skills;
    private Integer rating;
    private String status;
    private Date createdAt;
}
