package com.hackerrank.candidate.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.candidate.Entities.Applied;

public interface AppliedRepository extends JpaRepository<Applied, Integer>{
    List<Applied> findByCandidateId(Integer candidateId);
    Optional<Applied> findByJobIdAndCandidateId(Integer jobId, Integer candidateId);
}
