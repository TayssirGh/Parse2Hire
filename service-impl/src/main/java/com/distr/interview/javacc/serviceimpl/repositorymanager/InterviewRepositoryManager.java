package com.distr.interview.javacc.serviceimpl.repositorymanager;

import com.distr.interview.dalapilayer.InterviewEntity;
import com.distr.interview.dalpostgre.entity.InterviewJPAEntity;
import com.distr.interview.dalpostgre.repository.InterviewJPARepository;
import com.distr.interview.javacc.dal.mongodb.entity.InterviewMongoEntity;
import com.distr.interview.javacc.dal.mongodb.repo.InterviewRepository;
import com.distr.interview.javacc.infra.model.Interview;
import com.distr.interview.javacc.serviceimpl.converter.InterviewConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterviewRepositoryManager {
    @Autowired
    private InterviewRepository mongoRepository;
    @Autowired
    private InterviewJPARepository jpaRepository;
    public void onAddInterview(Interview interview) {
        InterviewMongoEntity mongoEntity = InterviewConverter.INSTANCE.toMongoEntity(interview);
        InterviewJPAEntity jpaEntity= InterviewConverter.INSTANCE.toJPAEntity(interview);
        mongoRepository.save(mongoEntity);
        jpaRepository.save(jpaEntity);
    }
    private void updateEntityFields(InterviewEntity entity, Interview interview) {
        entity.setId(interview.getId());
        entity.setInterviewDate(interview.getInterviewDate());
        entity.setInterviewerName(interview.getInterviewerName());
        entity.setCompanyName(interview.getCompanyName());
        entity.setCriteriaId(interview.getCriteriaId());

        entity.setCreatedAt(interview.getCreatedAt());
    }
    public void onUpdateCandidate(Interview interview) {
        InterviewJPAEntity postgresEntity = jpaRepository.findById(interview.getId())
                .orElseGet(InterviewJPAEntity::new);
        updateEntityFields(postgresEntity, interview);
        jpaRepository.save(postgresEntity);

        InterviewMongoEntity mongoEntity = mongoRepository.findById(interview.getId())
                .orElseGet(InterviewMongoEntity::new);
        updateEntityFields(mongoEntity, interview);
        mongoRepository.save(mongoEntity);
    }
    public void onDeleteInterview(String interviewId) {
        if (jpaRepository.existsById(interviewId)) {
            jpaRepository.deleteById(interviewId);
        } else {
            throw new IllegalArgumentException("Interview with ID " + interviewId + " does not exist in PostgreSQL");
        }

        if (mongoRepository.existsById(interviewId)) {
            mongoRepository.deleteById(interviewId);
        } else {
            throw new IllegalArgumentException("Interview with ID " + interviewId + " does not exist in MongoDB");
        }
    }
}
