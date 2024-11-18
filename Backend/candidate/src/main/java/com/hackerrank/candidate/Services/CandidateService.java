package com.hackerrank.candidate.Services;

import java.util.List;

import com.hackerrank.candidate.DTO.AppliedJobDTO;
import com.hackerrank.candidate.DTO.ApplyJobDTO;
import com.hackerrank.candidate.DTO.UpdateAppliedJobDTO;

public interface CandidateService {
    String login(String email, String password);
    String applyJob(ApplyJobDTO jobRequest);
    List<AppliedJobDTO> listOfAppliedJobs(Integer id);
    boolean updateAppliedJob(UpdateAppliedJobDTO updateAppliedJobDTO);
    boolean updateTestScore(Integer id, Integer testScore);
}
