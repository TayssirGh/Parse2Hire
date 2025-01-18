package com.distr.interview.dalpostgre.repository;

import com.distr.interview.dalpostgre.entity.InterviewJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewJPARepository extends JpaRepository<InterviewJPAEntity, String> {
}
