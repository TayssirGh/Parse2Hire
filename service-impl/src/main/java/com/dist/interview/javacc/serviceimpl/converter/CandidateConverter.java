package com.dist.interview.javacc.serviceimpl.converter;

import com.dist.interview.javacc.dal.mongodb.entity.CandidateEntity;
import com.dist.interview.javacc.infra.model.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CandidateConverter {
    CandidateConverter INSTANCE = Mappers.getMapper(CandidateConverter.class);
    CandidateEntity toEntity(Candidate c);
    Candidate fromEntity(CandidateEntity c);
    List<CandidateEntity> toEntityList(List<Candidate> candidates);
    List<Candidate> fromEntityList(List<CandidateEntity> candidateEntities);
}
