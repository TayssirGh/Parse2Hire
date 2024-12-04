package com.dist.interview.javacc.dal.mongodb.repo;

import com.dist.interview.javacc.dal.mongodb.entity.CriteriaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CriteriaRepository  extends MongoRepository<CriteriaEntity, String> {
}
