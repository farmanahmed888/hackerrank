package com.hackerrank.interviewer.Services;

import java.util.List;

import com.hackerrank.interviewer.DTO.CountDTO;
import com.hackerrank.interviewer.DTO.InterviewRecordInfoDTO;
import com.hackerrank.interviewer.DTO.JobEnrollDTO;
import com.hackerrank.interviewer.DTO.JobEnrollmentInfoDTO;
import com.hackerrank.interviewer.DTO.JobForCandidateDTO;
import com.hackerrank.interviewer.DTO.JobInfoDTO;
import com.hackerrank.interviewer.DTO.JobPostingDTO;
import com.hackerrank.interviewer.DTO.ScheduleInterviewDTO;
import com.hackerrank.interviewer.DTO.UpdateTestScoreDTO;
import com.hackerrank.interviewer.Entities.InterviewerRecord;
import com.hackerrank.interviewer.Entities.Job;

import java.util.Optional;


public interface InterviewService {
    String login(String email, String password);
    String createJob(JobPostingDTO jobPostingDTO);
    int activeJobsCount(int id);
    boolean enrollInJob(JobEnrollDTO jobEnrollDTO);
    boolean updateTestScore(UpdateTestScoreDTO updateTestScoreDTO);
    boolean interviewCodeEditorCheck(String interviewerEmail);

    List<JobInfoDTO> activeJobs(int id);
    List<Job> allActiveJobs();
    List<JobInfoDTO> getJobs(int id);
    List<JobEnrollmentInfoDTO> getJobEnrollment(int id);
    CountDTO getInterviewCount(int id);
    InterviewerRecord checkResults(int id);

    void scheduleInterview(ScheduleInterviewDTO scheduleInterviewDTO);
    void interviewResult(InterviewRecordInfoDTO interviewRecordInfoDTO);
    void closeJob(int jobId);
    
    Optional<JobForCandidateDTO> getJob(int id);
}
