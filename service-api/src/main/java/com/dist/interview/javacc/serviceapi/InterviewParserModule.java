package com.dist.interview.javacc.serviceapi;

import com.dist.interview.javacc.infra.model.Candidate;
import com.dist.interview.javacc.infra.model.Interview;
import net.thevpc.nuts.util.NOptional;

import java.util.List;

public interface InterviewParserModule {
//    ----------------candidate-crud----------------------------------
    NOptional<String> addCandidate(Candidate candidate);
    NOptional<List<Candidate>> findAllCandidates();
    NOptional<String> deleteCandidate(String candidate);
//    ----------------candidate-crud----------------------------------
    NOptional<String> addInterview(Interview interview);
    NOptional<List<Interview>> findAllInterviews();
    NOptional<String> deleteInterview(String interview);
}
