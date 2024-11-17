package com.dist.interview.javacc.dal.mongodb.repo;

import com.dist.interview.javacc.dal.mongodb.entity.CandidateCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends MongoRepository<CandidateCollection, String> {
}
