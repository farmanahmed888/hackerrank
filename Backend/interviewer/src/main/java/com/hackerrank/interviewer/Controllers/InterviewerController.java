package com.hackerrank.interviewer.Controllers;

import com.hackerrank.interviewer.DTO.*;
import com.hackerrank.interviewer.Entities.InterviewerRecord;
import com.hackerrank.interviewer.Entities.Job;
import com.hackerrank.interviewer.Services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class InterviewerController {
    @Autowired
    private InterviewService interviewerService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        String token = interviewerService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials or User is Unauthorized");
        }
    }
    @GetMapping("/activeJobs/{id}")
    public int activeJobsCountController(@PathVariable int id){
        return interviewerService.activeJobsCount(id);
    }

    @GetMapping("/all-active-jobs")
    public List<Job> allactiveJobsCountController(){

        List<Job> jobs =interviewerService.allactiveJobsCount();
        return jobs;

    }

    @GetMapping("/closed-jobs/{id}")
    public List<JobInfoDTO> closedJobController(@PathVariable int id){
        return interviewerService.getClosedJobs(id);

    }

    @PostMapping("/scheduleInterview")
    public void scheduleInterview(@RequestBody ScheduleInterviewDTO ScheduleInterviewDTO){
        interviewerService.scheduleInterview(ScheduleInterviewDTO);
    }
    @PostMapping("/result")
    public void interviewResults(@RequestBody InterviewRecordInfoDTO dto){
        interviewerService.interviewResult(dto);
    }
    @GetMapping("/count/{id}")
    public CountDTO counter(@PathVariable int id){
        return interviewerService.counter(id);
    }

    @PostMapping("/createJob")
    public ResponseEntity<String> createJob(@RequestBody JobPostingDTO jobPostingRequest) {
        String message = interviewerService.createJob(jobPostingRequest);
        if (message.equals("Job created successfully")) {
            return ResponseEntity.ok(message);
        } else if (message.equals("Interviewer not found for the given ID")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<List<JobInfoDTO>> getJobs(@PathVariable int id) {

        List<JobInfoDTO> jobs = interviewerService.getJobs(id);
        if (!jobs.isEmpty()) {
            return ResponseEntity.ok(jobs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Optional<JobForCandidateDTO>> getJob(@PathVariable int id) {

        Optional<JobForCandidateDTO> job = interviewerService.getJob(id);
        if (job.isPresent()) {
            return ResponseEntity.ok(job);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/job/enroll")
    public ResponseEntity<String> enrollInJob(@RequestBody JobEnrollDTO jobEnrollRequest) {

        boolean status = interviewerService.enrollInJob(jobEnrollRequest);
        if (status) {
            return ResponseEntity.ok("Enrollment Successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in enrollment");
        }
    }

    @GetMapping("/jobEnrollments/{jobId}")
    public ResponseEntity<List<JobEnrollmentInfoDTO>> getJobEnrollments(@PathVariable int jobId) {
        List<JobEnrollmentInfoDTO> jobs = interviewerService.getJobEnrollments(jobId);
        if (!jobs.isEmpty()) {
            return ResponseEntity.ok(jobs);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/job/updateTestScore")
    public ResponseEntity<String> updateTestScore(@RequestBody UpdateTestScoreDTO updateTestScoreRequest) {
        boolean status = interviewerService.updateTestScore(updateTestScoreRequest);
        if (status) {
            return ResponseEntity.ok("Test Score Updated Successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Test Score Updation");
        }
    }

    @PostMapping("/code-sync-candidate-check/{candidateId}")
    public boolean candidateCheck(@PathVariable String candidateId, @RequestBody String roomId){
        CandidateCheckDTO dto = new CandidateCheckDTO();
        dto.setCandidateName(candidateId);
        dto.setRoomId(roomId);
        return interviewerService.candidateCodeEditorCheck(dto);
    }

    @PostMapping("/code-sync-interviewer-check/{interviewerEmail}")
    public boolean interviewerCheck(@PathVariable String interviewerEmail){
        return interviewerService.interviewerCodeEditorCheck(interviewerEmail);
    }
    @GetMapping("/result/{enrollId}")
    public InterviewerRecord results(@PathVariable int enrollId){
        return interviewerService.checkResults(enrollId);
    }
    @PostMapping("/close-job/{id}")
    public void closeJob(@PathVariable int id){
        interviewerService.closeJob(id);
    }
}
