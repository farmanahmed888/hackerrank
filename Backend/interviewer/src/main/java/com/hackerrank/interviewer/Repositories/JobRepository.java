package com.hackerrank.interviewer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.interviewer.Entities.Job;

import java.util.List;
public interface JobRepository extends JpaRepository<Job,Integer>{
    List<Job> findByInterviewerId(Integer interviewerId);
    int countAllByInterviewerId(Integer interviewerId);
    List<Job> findByStatus(String status);
}
