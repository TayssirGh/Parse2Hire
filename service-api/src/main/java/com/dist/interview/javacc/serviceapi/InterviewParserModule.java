package com.dist.interview.javacc.serviceapi;

import com.dist.interview.javacc.infra.model.Candidate;

import java.util.List;

public interface InterviewParserModule {
    String addCandidate(Candidate candidate);
    List<Candidate> findAllCandidates();
    String deleteCandidate(String candidate);
}
