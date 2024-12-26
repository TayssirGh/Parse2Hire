package com.dist.interview.javacc.serviceapi;

import com.dist.interview.javacc.infra.model.Candidate;
import com.dist.interview.javacc.infra.model.Criteria;
import com.dist.interview.javacc.infra.model.EmailRequest;
import com.dist.interview.javacc.infra.model.Interview;
import net.thevpc.nuts.util.NOptional;

import java.util.List;

public interface InterviewParserModule {
//    ----------------candidate-crud----------------------------------
    Candidate addCandidate(Candidate candidate);
    NOptional<List<Candidate>> findAllCandidates();
    NOptional<String> deleteCandidate(String candidate);
//    ----------------interview-crud----------------------------------
    Interview addInterview(Interview interview);
    NOptional<List<Interview>> findAllInterviews();
    NOptional<String> deleteInterview(String interview);
//    ----------------criteria-crud----------------------------------
    Criteria addCriteria(Criteria criteria);
    NOptional<List<Criteria>> findAllCriterias();
    NOptional<String> deleteCriteria(String interview);


    void sendEmail(EmailRequest emailRequest);
}
