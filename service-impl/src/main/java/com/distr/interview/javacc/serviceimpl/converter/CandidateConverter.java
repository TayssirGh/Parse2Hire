package com.distr.interview.javacc.serviceimpl.converter;

import com.distr.interview.dalpostgre.entity.CandidateJPAEntity;
import com.distr.interview.javacc.dal.mongodb.entity.CandidateMongoEntity;
import com.distr.interview.javacc.infra.model.Candidate;
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
