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
@Document(collection = "criteria")
public class CriteriaMongoEntity {
    @Id
    private String id;
    private String position;
    private Double minimumScore;
    private Integer maximumResponseTime;
    private List<String> requiredSkills;
    private String candidateId;
    private Date createdAt;
    private Integer maximumRank;
}
