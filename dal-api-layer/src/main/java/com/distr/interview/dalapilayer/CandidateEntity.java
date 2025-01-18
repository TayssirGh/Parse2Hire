package com.distr.interview.dalapilayer;

import com.distr.interview.javacc.infra.model.Status;

import java.util.Date;
import java.util.List;

public interface CandidateEntity {
    void setInterviewId(String interviewId);
    void setName(String name);
    void setEmail(String email);
    void setAppliedPosition(String appliedPosition);
    void setSkills(List<String> skills);
    void setInterviewScore(Double interviewScore);
    void setResponseTime(Integer responseTime);
    void setFeedback(String feedback);
    void setRating(Double rating);
    void setStatus(Status status);
    void setLocation(String location);
    void setCreatedAt(Date createdAt);
}
