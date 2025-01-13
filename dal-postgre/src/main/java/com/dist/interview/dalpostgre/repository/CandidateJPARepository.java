package com.dist.interview.dalpostgre.repository;

import com.dist.interview.dalpostgre.entity.CandidateJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateJPARepository extends JpaRepository<CandidateJPAEntity, Long> {
}
