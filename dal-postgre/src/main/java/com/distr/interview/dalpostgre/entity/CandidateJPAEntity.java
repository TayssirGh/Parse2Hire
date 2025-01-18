package com.distr.interview.dalpostgre.entity;

import com.distr.interview.dalapilayer.CandidateEntity;
import com.distr.interview.javacc.infra.model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "candidate")
public class CandidateJPAEntity implements CandidateEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "interview_id", nullable = false)
    private String interviewId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "applied_position", nullable = false)
    private String appliedPosition;

    @ElementCollection
    @CollectionTable(name = "candidate_skills", joinColumns = @JoinColumn(name = "candidate_id"))
    @Column(name = "skill")
    private List<String> skills;

    @Column(name = "interview_score")
    private Double interviewScore;

    @Column(name = "response_time")
    private Integer responseTime;

    private String feedback;

    private Double rating;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String location;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
