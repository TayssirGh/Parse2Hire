package com.dist.interview.javacc.serviceimpl.converter;

import com.dist.interview.javacc.dal.mongodb.entity.CandidateEntity;
import com.dist.interview.javacc.infra.model.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidateConverter {
    public CandidateConverter INSTANCE = Mappers.getMapper(CandidateConverter.class);
    public CandidateEntity toEntity(Candidate c);
    public Candidate fromEntity(CandidateEntity c);
}
