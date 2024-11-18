package com.hackerrank.candidate.Services;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hackerrank.candidate.DTO.CandidateJobDTO;
import com.hackerrank.candidate.DTO.EnrollJobDTO;

@FeignClient(name = "interview-service")
public interface InterviewClientService {
    @GetMapping("/interviews/job/{jobId}")
    public ResponseEntity<Optional<CandidateJobDTO>> getJobById(@PathVariable Integer jobId);
    @PostMapping("/interviewer/job/enroll")
    public ResponseEntity<String> enrollInJob(@PathVariable EnrollJobDTO jobRequest);
}
