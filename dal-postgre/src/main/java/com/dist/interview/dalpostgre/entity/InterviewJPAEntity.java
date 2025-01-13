package com.dist.interview.dalpostgre.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "interview")
public class InterviewJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "criteria_id", nullable = false)
    private String criteriaId;

    @Column(name = "interview_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date interviewDate;

    @Column(name = "interviewer_name", nullable = false)
    private String interviewerName;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
