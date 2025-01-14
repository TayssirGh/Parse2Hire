package com.dist.interview.javacc.serviceimpl.entity;

import com.dist.interview.javacc.dal.mongodb.repo.CandidateRepository;
import com.dist.interview.javacc.infra.model.Candidate;
import com.dist.interview.javacc.serviceimpl.repositorymanager.CandidateRepositoryManager;
import net.thevpc.nuts.util.NAssert;
import net.thevpc.nuts.util.NOptional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepositoryManager candidateRepositoryManager;
    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate addCandidate(Candidate candidate) {
        NAssert.requireTrue(candidate != null, "candidate must not be null");
        candidateRepositoryManager.addCandidate(candidate);
        return candidate;
    }
    public NOptional<List<Candidate>> findAllCandidates(){
        return NOptional.of(candidateRepository.findAll()
                .stream()
                .map(collection -> new Candidate(collection.getId(), collection.getInterviewId(), collection.getName(),
                        collection.getEmail(), collection.getAppliedPosition(), collection.getSkills(),collection.getInterviewScore(),
                        collection.getResponseTime(), collection.getFeedback(), collection.getRating(),
                        collection.getStatus(), collection.getCreatedAt()))
                .collect(Collectors.toList())
        );
    }
    public NOptional<String> deleteCandidate(String id){
        if (candidateRepository.existsById(id)) {
            candidateRepositoryManager.deleteCandidate(id);
            return NOptional.of(id);
        }
        throw new IllegalArgumentException("Candidate with ID " + id + " does not exist");
    }


    public Candidate updateCandidate(Candidate candidate) {
        NAssert.requireTrue(candidate != null, "candidate must not be null");
        NAssert.requireTrue(candidate.getId() != null, "candidate ID must not be null");
        candidateRepositoryManager.updateCandidate(candidate);
        return candidate;
    }
}
