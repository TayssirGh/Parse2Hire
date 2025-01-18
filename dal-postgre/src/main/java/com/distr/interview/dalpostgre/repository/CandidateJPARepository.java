package com.distr.interview.dalpostgre.repository;

import com.distr.interview.dalpostgre.entity.CandidateJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateJPARepository extends JpaRepository<CandidateJPAEntity, String> {
}
