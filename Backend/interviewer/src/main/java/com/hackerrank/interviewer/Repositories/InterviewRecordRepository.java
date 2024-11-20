package com.hackerrank.interviewer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.interviewer.Entities.InterviewerRecord;
import com.hackerrank.interviewer.Entities.Enrollment;

public interface InterviewRecordRepository extends JpaRepository<InterviewerRecord,Integer>{
    InterviewerRecord findByEnrollment(Enrollment enrollment);
}
