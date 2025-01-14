package com.dist.interview.javacc.serviceimpl.converter;

import com.dist.interview.dalpostgre.entity.CandidateJPAEntity;
import com.dist.interview.javacc.dal.mongodb.entity.CandidateMongoEntity;
import com.dist.interview.javacc.infra.model.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CandidateConverter {
    CandidateConverter INSTANCE = Mappers.getMapper(CandidateConverter.class);
    CandidateMongoEntity toMongoEntity(Candidate c);
    CandidateJPAEntity toJPAEntity(Candidate c);
    Candidate fromMongoEntity(CandidateMongoEntity c);
    Candidate fromJPAEntity(CandidateJPAEntity c);
    List<CandidateMongoEntity> toEntityList(List<Candidate> candidates);
    List<Candidate> fromEntityList(List<CandidateMongoEntity> candidateEntities);
}
