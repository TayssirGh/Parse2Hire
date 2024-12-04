package com.dist.interview.javacc.serviceimpl.entity;

import com.dist.interview.javacc.dal.mongodb.entity.CriteriaEntity;
import com.dist.interview.javacc.dal.mongodb.repo.CriteriaRepository;
import com.dist.interview.javacc.infra.model.Criteria;
import com.dist.interview.javacc.serviceimpl.converter.CritireaConverter;
import net.thevpc.nuts.util.NAssert;
import net.thevpc.nuts.util.NOptional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriteriaService {
    @Autowired
    private CriteriaRepository criteriaRepository;

    public Criteria addCriteria(Criteria criteria) {
        NAssert.requireTrue(criteria != null, "criteria must not be null");
        CriteriaEntity c = CritireaConverter.INSTANCE.toEntity(criteria);
        criteriaRepository.insert(c);
        criteriaRepository.save(c);
        return criteria;
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
