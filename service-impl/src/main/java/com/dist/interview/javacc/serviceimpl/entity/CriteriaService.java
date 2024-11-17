package com.dist.interview.javacc.serviceimpl.entity;

import com.dist.interview.javacc.dal.mongodb.entity.CriteriaCollection;
import com.dist.interview.javacc.dal.mongodb.repo.CriteriaRepository;
import com.dist.interview.javacc.infra.model.Criteria;
import net.thevpc.nuts.util.NOptional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriteriaService {
    private CriteriaRepository criteriaRepository;

    public NOptional<String> addCriteria(Criteria criteria) {
        CriteriaCollection criteriaCollection = new CriteriaCollection();
        criteriaCollection.setId(criteria.getId());
        criteriaCollection.setId(criteria.getId());
        criteriaCollection.setPosition(criteria.getPosition());
        criteriaCollection.setMinimumScore(criteria.getMinimumScore());
        criteriaCollection.setMaximumResponseTime(criteria.getMaximumResponseTime());
        criteriaCollection.setRequiredSkills(criteria.getRequiredSkills());
        criteriaCollection.setCriteriaDescription(criteria.getCriteriaDescription());
        criteriaCollection.setCandidateIds(criteria.getCandidateIds());
        criteriaCollection.setCreatedAt(criteria.getCreatedAt());

        criteriaRepository.insert(criteriaCollection);
        criteriaRepository.save(criteriaCollection);
        return NOptional.of(criteria.getId());
    }

    public NOptional<List<Criteria>> findAllCriterias() {
        return NOptional.of(criteriaRepository.findAll()
                .stream()
                .map(collection -> new Criteria(
                        collection.getId(),
                        collection.getPosition(),
                        collection.getMinimumScore(),
                        collection.getMaximumResponseTime(),
                        collection.getRequiredSkills(),
                        collection.getCriteriaDescription(),
                        collection.getCandidateIds(),
                        collection.getCreatedAt()
                ))
                .collect(Collectors.toList())
        );
    }

    public NOptional<String> deleteCriteria(String id) {
        criteriaRepository.deleteById(id);
        return NOptional.of(id);
    }
}
