package com.distr.interview.javacc.dal.mongodb.entity;

import com.distr.interview.dalapilayer.CandidateEntity;
import com.distr.interview.javacc.infra.model.Status;
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
public class CandidateMongoEntity implements CandidateEntity {
    @Id
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
