package com.dist.interview.javacc.dal.mongodb.repo;

import com.dist.interview.javacc.dal.mongodb.entity.CriteriaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository  extends MongoRepository<CriteriaEntity, String> {
}
