package com.dist.interview.javacc.serviceapi;

import com.dist.interview.javacc.infra.model.Candidate;
import net.thevpc.nuts.util.NOptional;

import java.util.List;

public interface InterviewParserModule {
    NOptional<String> addCandidate(Candidate candidate);
    NOptional<List<Candidate>> findAllCandidates();
    NOptional<String> deleteCandidate(String candidate);
}
