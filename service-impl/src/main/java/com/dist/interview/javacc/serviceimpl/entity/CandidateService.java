package com.dist.interview.javacc.serviceimpl.entity;

import com.dist.interview.javacc.dal.mongodb.entity.CandidateEntity;
import com.dist.interview.javacc.dal.mongodb.repo.CandidateRepository;
import com.dist.interview.javacc.infra.model.Candidate;
import com.dist.interview.javacc.serviceimpl.converter.CandidateConverter;
import net.thevpc.nuts.util.NAssert;
import net.thevpc.nuts.util.NOptional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    public Candidate addCandidate(Candidate candidate) {
        NAssert.requireTrue(candidate != null, "candidate must not be null");
        CandidateEntity c = CandidateConverter.INSTANCE.toEntity(candidate);
        candidateRepository.insert(c);
        candidateRepository.save(c);
       return candidate;
    }
    public NOptional<List<Candidate>> findAllCandidates(){
        return NOptional.of(candidateRepository.findAll()
                .stream()
                .map(collection -> new Candidate(collection.getId(), collection.getInterviewId(), collection.getName(),
                        collection.getEmail(), collection.getAppliedPosition(), collection.getSkills(),collection.getRating(),
                        collection.getStatus(), collection.getCreatedAt()))
                .collect(Collectors.toList())
        );
    }
    public NOptional<String> deleteCandidate(String id){
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            return NOptional.of(id);
        }
        else {
            throw new RuntimeException();
        }
    }
}
