package com.dist.interview.javacc.serviceimpl.entity;

import com.dist.interview.javacc.dal.mongodb.repo.InterviewRepository;
import com.dist.interview.javacc.infra.model.Interview;
import com.dist.interview.javacc.serviceimpl.converter.InterviewConverter;
import com.dist.interview.javacc.serviceimpl.repositorymanager.InterviewRepositoryManager;
import net.thevpc.nuts.util.NAssert;
import net.thevpc.nuts.util.NOptional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private InterviewRepositoryManager interceptor;

    public Interview addInterview(Interview interview) {
        NAssert.requireTrue(interview != null, "interview must not be null");
        interceptor.onAddInterview(interview);
        return interview;
    }

    public NOptional<List<Interview>> findAllInterviews() {
        return NOptional.of(interviewRepository.findAll()
                .stream()
                .map(collection -> new Interview(collection.getId(),
                        collection.getCriteriaId(),
                        collection.getInterviewDate(),
                        collection.getInterviewerName(),
                        collection.getCompanyName(),
                        collection.getCreatedAt()))
                .collect(Collectors.toList())
        );
    }

    public NOptional<Interview> findInterviewById(String id) {
        NAssert.requireTrue(id != null && !id.isEmpty(), "id must not be null or empty");
        return interviewRepository.findById(id)
                .map(InterviewConverter.INSTANCE::fromMongoEntity)
                .map(NOptional::of)
                .orElse(NOptional.ofEmpty());
    }

    public NOptional<String> deleteInterview(String id) {
        interviewRepository.deleteById(id);
        return NOptional.of(id);

    }
}
