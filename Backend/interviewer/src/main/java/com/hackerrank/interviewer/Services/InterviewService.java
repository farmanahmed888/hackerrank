package com.hackerrank.interviewer.Services;

import com.hackerrank.interviewer.DTO.*;
import com.hackerrank.interviewer.Entities.InterviewerRecord;
import com.hackerrank.interviewer.Entities.Job;

import java.util.List;
import java.util.Optional;

public interface InterviewService {
    String login(String email, String password);
    int activeJobsCount(int id);
    List<JobInfoDTO> getClosedJobs(int id);
    List<Job> allactiveJobsCount();
    String createJob(JobPostingDTO jobPostingRequest);

    List<JobInfoDTO> getJobs(int id);

    List<JobEnrollmentInfoDTO> getJobEnrollments(Integer jobId);
    void scheduleInterview(ScheduleInterviewDTO dto);
    CountDTO counter(int id);
    void interviewResult(InterviewRecordInfoDTO dto);
    boolean enrollInJob(JobEnrollDTO jobEnrollRequest);
    void closeJob(int jobId);
    boolean updateTestScore(UpdateTestScoreDTO updateTestScoreRequest);
    Optional<JobForCandidateDTO> getJob(int id);
    boolean candidateCodeEditorCheck(CandidateCheckDTO CandidateCheckDTO);
    boolean interviewerCodeEditorCheck(String interviewerEmail);
    InterviewerRecord checkResults(int enrollId);
}
