package com.distr.interview.javacc.dal.mongodb.repo;

import com.distr.interview.javacc.dal.mongodb.entity.InterviewMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends MongoRepository<InterviewMongoEntity, String> {
}
