package com.hackerrank.candidate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.candidate.Entities.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
    Candidate findByEmail(String email);
}
