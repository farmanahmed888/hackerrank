package com.hackerrank.interviewer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.interviewer.Entities.Interviewer;

public interface InterviewRepository extends JpaRepository<Interviewer, Integer> {
    Interviewer findByEmail(String email);
}
