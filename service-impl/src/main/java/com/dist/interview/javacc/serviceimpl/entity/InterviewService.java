package com.dist.interview.javacc.serviceimpl.entity;

import com.dist.interview.javacc.dal.mongodb.entity.InterviewCollection;
import com.dist.interview.javacc.dal.mongodb.repo.InterviewRepository;
import com.dist.interview.javacc.infra.model.Interview;
import net.thevpc.nuts.util.NOptional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;
    public NOptional<String> addInterview(Interview interview) {
        InterviewCollection interviewCollection = new InterviewCollection();
        interviewCollection.setId(interview.getId());
        interviewCollection.setCandidateId(interview.getCandidateId());
        interviewCollection.setCriteriaId(interview.getCriteriaId());
        interviewCollection.setInterviewerName(interview.getInterviewerName());
        interviewCollection.setCompanyName(interview.getCompanyName());
        interviewCollection.setInterviewScore(interview.getInterviewScore());
        interviewCollection.setResponseTime(interview.getResponseTime());
        interviewCollection.setFeedback(interview.getFeedback());
        interviewCollection.setRating(interview.getRating());
        interviewCollection.setResult(interview.getResult());
        interviewCollection.setCreatedAt(interview.getCreatedAt());

        interviewRepository.insert(interviewCollection);
        interviewRepository.save(interviewCollection);
        return NOptional.of(interview.getId());
    }
    public NOptional<List<Interview>> findAllInterviews(){
        return NOptional.of(interviewRepository.findAll()
                .stream()
                .map(collection -> new Interview(collection.getId(),
                        collection.getCandidateId(),
                        collection.getCriteriaId(),
                        collection.getInterviewDate(),
                        collection.getInterviewerName(),
                        collection.getCompanyName(),
                        collection.getInterviewScore(),
                        collection.getResponseTime(),
                        collection.getFeedback(),
                        collection.getRating(),
                        collection.getResult(),
                        collection.getCreatedAt()))
                .collect(Collectors.toList())
        );
    }
    public NOptional<String> deleteInterview(String id){
            return NOptional.of(id);

    }
}
