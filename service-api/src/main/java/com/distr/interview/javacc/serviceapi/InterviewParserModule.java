package com.distr.interview.javacc.serviceapi;

import com.distr.interview.javacc.infra.model.*;
import com.distr.interview.javacc.infra.model.*;
import net.thevpc.nuts.util.NOptional;

import java.util.List;

public interface InterviewParserModule {
//    ----------------candidate-crud----------------------------------
    Candidate addCandidate(Candidate candidate);
    NOptional<List<Candidate>> findAllCandidates();
    NOptional<String> deleteCandidate(String candidate);
    Candidate updateCandidate(Candidate candidate);
//    ----------------interview-crud----------------------------------
    Interview addInterview(Interview interview);
    NOptional<List<Interview>> findAllInterviews();
    NOptional<String> deleteInterview(String interview);
//    ----------------criteria-crud----------------------------------
//    Criteria addCriteria(Criteria criteria);
//    NOptional<List<Criteria>> findAllCriterias();
//    NOptional<String> deleteCriteria(String interview);
//    ----------------parser-logic---------------------------------
    ParsedQuery parseQuery(ParserInput query);
    List<Candidate> findCandidatesByParsedQuery(ParsedQuery parsedQuery);
    List<Candidate> sendParsedEmail(ParserInput input);
    void sendEmail(EmailRequest emailRequest);
}
