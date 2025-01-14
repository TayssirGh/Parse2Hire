package com.dist.interview.javacc.serviceimpl.converter;

import com.dist.interview.dalpostgre.entity.InterviewJPAEntity;
import com.dist.interview.javacc.dal.mongodb.entity.InterviewMongoEntity;
import com.dist.interview.javacc.infra.model.Interview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterviewConverter {
    InterviewConverter INSTANCE = Mappers.getMapper(InterviewConverter.class);
    InterviewMongoEntity toMongoEntity(Interview interview);
    InterviewJPAEntity toJPAEntity(Interview interview);
    Interview fromMongoEntity(InterviewMongoEntity interviewMongoEntity);
    Interview fromJpaEntity(InterviewJPAEntity interviewMongoEntity);
}
