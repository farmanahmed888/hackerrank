package com.hackerrank.interviewer.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.interviewer.Entities.Enrollment;
import com.hackerrank.interviewer.Entities.Job;

public interface  EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    Optional<Enrollment> findByCandidateIdAndJobId(Integer candidateId, Integer jobId);
    Optional<Enrollment> findById(Integer id);

    List<Enrollment> findByRoomId(Integer enroll);
    List<Enrollment> findByJobId(Job job);
    List<Enrollment> findByJobId(Integer jobId);
    Enrollment findByCandidateNameandRoomId(String candidateId, String roomId);
}
