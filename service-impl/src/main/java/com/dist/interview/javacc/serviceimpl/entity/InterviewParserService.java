package com.dist.interview.javacc.serviceimpl.entity;

import com.dist.interview.javacc.dal.mongodb.entity.CandidateCollection;
import com.dist.interview.javacc.dal.mongodb.repo.CandidateRepository;
import com.dist.interview.javacc.infra.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewParserService {
    @Autowired
    private CandidateRepository candidateRepository;
    public String addCandidate(Candidate candidate) {
        CandidateCollection candidateCollection = new CandidateCollection();
        candidateCollection.setId(candidate.getId());
        candidateCollection.setName(candidate.getName());
        candidateCollection.setEmail(candidate.getEmail());
        candidateCollection.setSkills(candidate.getSkills());
        candidateCollection.setAppliedPosition(candidate.getAppliedPosition());
        candidateCollection.setStatus(candidate.getStatus());
        candidateCollection.setCreatedAt(candidate.getCreatedAt());
        candidateRepository.insert(candidateCollection);
        candidateRepository.save(candidateCollection);
       return candidate.getId();
    }
    public List<Candidate> findAllCandidates(){
        return candidateRepository.findAll()
                .stream()
                .map(collection -> new Candidate(collection.getId(), collection.getName(),
                        collection.getEmail(), collection.getAppliedPosition(), collection.getSkills(),
                        collection.getStatus(), collection.getCreatedAt()))
                .collect(Collectors.toList());
    }
    public String deleteCandidate(String id){
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            return id;
        }
        else {
            throw new RuntimeException();
        }
    }
}
