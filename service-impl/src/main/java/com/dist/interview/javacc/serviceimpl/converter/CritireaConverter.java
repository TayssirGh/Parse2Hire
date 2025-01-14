package com.dist.interview.javacc.serviceimpl.converter;

import com.dist.interview.javacc.dal.mongodb.entity.CriteriaMongoEntity;
import com.dist.interview.javacc.infra.model.Criteria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CritireaConverter {
    CritireaConverter INSTANCE = Mappers.getMapper(CritireaConverter.class);
    CriteriaMongoEntity toEntity(Criteria c);
    Criteria fromEntity(CriteriaMongoEntity c);
}
