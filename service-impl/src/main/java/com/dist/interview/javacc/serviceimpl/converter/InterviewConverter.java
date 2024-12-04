package com.dist.interview.javacc.serviceimpl.converter;

import com.dist.interview.javacc.dal.mongodb.entity.InterviewEntity;
import com.dist.interview.javacc.infra.model.Interview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterviewConverter {
    public InterviewConverter INSTANCE = Mappers.getMapper(InterviewConverter.class);
    public InterviewEntity toEntity(Interview interview);
    public Interview fromEntity(InterviewEntity interviewEntity);
}
