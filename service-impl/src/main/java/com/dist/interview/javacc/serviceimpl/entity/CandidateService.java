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
                        collection.getEmail(), collection.getAppliedPosition(), collection.getSkills(),collection.getInterviewScore(),
                        collection.getResponseTime(), collection.getFeedback(), collection.getRating(),
                        collection.getStatus(), collection.getCreatedAt()))
                .collect(Collectors.toList())
        );
    }
    public NOptional<String> deleteCandidate(String id){
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            return NOptional.of(id);
        }
        throw new IllegalArgumentException("Candidate with ID " + id + " does not exist");
    }

    public Candidate updateCandidate(Candidate candidate) {
        NAssert.requireTrue(candidate != null, "candidate must not be null");
        NAssert.requireTrue(candidate.getId() != null, "candidate ID must not be null");

        CandidateEntity existingEntity = candidateRepository.findById(candidate.getId())
                .orElseThrow(() -> new IllegalArgumentException("Candidate with ID " + candidate.getId() + " does not exist"));

        existingEntity.setInterviewId(candidate.getInterviewId());
        existingEntity.setName(candidate.getName());
        existingEntity.setEmail(candidate.getEmail());
        existingEntity.setAppliedPosition(candidate.getAppliedPosition());
        existingEntity.setSkills(candidate.getSkills());
        existingEntity.setInterviewScore(candidate.getInterviewScore());
        existingEntity.setResponseTime(candidate.getResponseTime());
        existingEntity.setFeedback(candidate.getFeedback());
        existingEntity.setRating(candidate.getRating());
        existingEntity.setStatus(candidate.getStatus());
        existingEntity.setCreatedAt(candidate.getCreatedAt());

        candidateRepository.save(existingEntity);

        return CandidateConverter.INSTANCE.fromEntity(existingEntity);
    }
}
