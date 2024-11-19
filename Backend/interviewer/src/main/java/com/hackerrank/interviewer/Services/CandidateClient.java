package com.hackerrank.interviewer.Services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import com.hackerrank.interviewer.DTO.AppliedJobDTO;
import com.hackerrank.interviewer.DTO.updateAppliedJobDTO;

@FeignClient(name = "candidateService")
public interface CandidateClient {
    @GetMapping("/appliedJobs/{id}")
    ResponseEntity<List<AppliedJobDTO>> AppliedJobList(@PathVariable Integer id);
    @PostMapping("/candidate/updateAppliedJob")
    ResponseEntity<String> updateAppliedJob(@RequestBody updateAppliedJobDTO updateAppliedJobDTO);
}
