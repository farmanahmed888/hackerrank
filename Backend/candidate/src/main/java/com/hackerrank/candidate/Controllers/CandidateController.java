package com.hackerrank.candidate.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.candidate.DTO.AppliedJobDTO;
import com.hackerrank.candidate.DTO.ApplyJobDTO;
import com.hackerrank.candidate.DTO.LoginRequestDTO;
import com.hackerrank.candidate.DTO.UpdateAppliedJobDTO;
import com.hackerrank.candidate.DTO.UpdateTestScoreDTO;
import com.hackerrank.candidate.Services.CandidateService;



@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        //TODO: process POST request
        String response = candidateService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if(response!=null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials");
    }
    @PostMapping("/apply")
    public ResponseEntity<String> applyJob(@RequestBody ApplyJobDTO jobRequest) {
        //TODO: process POST request
        String response = candidateService.applyJob(jobRequest);
        if(response!=null) {
            return ResponseEntity.ok("Job applied successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to apply for job");
    }
    @PostMapping("/updateApplied")
    public ResponseEntity<String> updateAppliedJob(@RequestBody UpdateAppliedJobDTO updateAppliedJobDTO) {
        //TODO: process POST request
        boolean response = candidateService.updateAppliedJob(updateAppliedJobDTO);
        if(response) {
            return ResponseEntity.ok("Job updated successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update job");
    }
    @GetMapping("/appliedJobs/{id}")
    public ResponseEntity<List<AppliedJobDTO>> listOfAppliedJobs(@RequestParam Integer id) {
        List<AppliedJobDTO> response = candidateService.listOfAppliedJobs(id);
        if(response!=null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    @PatchMapping("/updateTestScore/{id}")
    public ResponseEntity<String> updateTestScore(@PathVariable Integer id, @RequestBody UpdateTestScoreDTO updateTestScoreDTO) {
        boolean response = candidateService.updateTestScore(id, updateTestScoreDTO.getTestScore());
        if(response) {
            return ResponseEntity.ok("Test score updated successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update test score");
    }
    
}
