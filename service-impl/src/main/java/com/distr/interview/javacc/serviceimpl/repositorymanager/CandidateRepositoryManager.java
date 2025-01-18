package com.distr.interview.javacc.serviceimpl.repositorymanager;

import com.distr.interview.dalapilayer.CandidateEntity;
import com.distr.interview.dalpostgre.entity.CandidateJPAEntity;
import com.distr.interview.dalpostgre.repository.CandidateJPARepository;
import com.distr.interview.javacc.dal.mongodb.entity.CandidateMongoEntity;
import com.distr.interview.javacc.dal.mongodb.repo.CandidateRepository;
import com.distr.interview.javacc.infra.model.Candidate;
import com.distr.interview.javacc.serviceimpl.converter.CandidateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CandidateRepositoryManager {
    @Autowired
    private CandidateRepository mongoRepository;
    @Autowired
    private CandidateJPARepository jpaRepository;
    public void addCandidate(Candidate candidate) {
        if (candidate.getId() == null || candidate.getId().isEmpty()) {
            String generatedId = UUID.randomUUID().toString();
            candidate.setId(generatedId);
        }
        CandidateMongoEntity mongoEntity = CandidateConverter.INSTANCE.toMongoEntity(candidate);
        CandidateJPAEntity jpaEntity= CandidateConverter.INSTANCE.toJPAEntity(candidate);
        mongoRepository.save(mongoEntity);
        jpaRepository.save(jpaEntity);
    }
    private void updateEntityFields(CandidateEntity entity, Candidate candidate) {
        entity.setInterviewId(candidate.getInterviewId());
        entity.setName(candidate.getName());
        entity.setEmail(candidate.getEmail());
        entity.setAppliedPosition(candidate.getAppliedPosition());
        entity.setSkills(candidate.getSkills());
        entity.setInterviewScore(candidate.getInterviewScore());
        entity.setResponseTime(candidate.getResponseTime());
        entity.setFeedback(candidate.getFeedback());
        entity.setRating(candidate.getRating());
        entity.setStatus(candidate.getStatus());
        entity.setLocation(candidate.getLocation());
        entity.setCreatedAt(candidate.getCreatedAt());
    }
    public void updateCandidate(Candidate candidate) {
        CandidateJPAEntity postgresEntity = jpaRepository.findById(candidate.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Candidate with ID " + candidate.getId() + " does not exist in PostgreSQL"
                ));
        updateEntityFields(postgresEntity, candidate);
        jpaRepository.save(postgresEntity);

        CandidateMongoEntity mongoEntity = mongoRepository.findById(candidate.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Candidate with ID " + candidate.getId() + " does not exist in MongoDB"
                ));
        updateEntityFields(mongoEntity, candidate);
        mongoRepository.save(mongoEntity);
    }
    public void deleteCandidate(String candidateId) {
        if (jpaRepository.existsById(candidateId)) {
            jpaRepository.deleteById(candidateId);
        } else {
            throw new IllegalArgumentException("Candidate with ID " + candidateId + " does not exist in PostgreSQL");
        }

        if (mongoRepository.existsById(candidateId)) {
            mongoRepository.deleteById(candidateId);
        } else {
            throw new IllegalArgumentException("Candidate with ID " + candidateId + " does not exist in MongoDB");
        }
    }

}
