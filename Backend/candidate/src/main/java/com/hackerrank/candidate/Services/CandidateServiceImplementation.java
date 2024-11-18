package com.hackerrank.candidate.Services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hackerrank.candidate.DTO.AppliedJobDTO;
import com.hackerrank.candidate.DTO.ApplyJobDTO;
import com.hackerrank.candidate.DTO.CandidateJobDTO;
import com.hackerrank.candidate.DTO.EnrollJobDTO;
import com.hackerrank.candidate.DTO.UpdateAppliedJobDTO;
import com.hackerrank.candidate.Entities.Applied;
import com.hackerrank.candidate.Entities.Candidate;
import com.hackerrank.candidate.Repositories.AppliedRepository;
import com.hackerrank.candidate.Repositories.CandidateRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CandidateServiceImplementation implements CandidateService {
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    private AppliedRepository appliedRepository;
    private InterviewClientService interviewClient;

    @Override
    public String login(String email, String password) {
        // TODO Auto-generated method stub
        Candidate candidate = candidateRepository.findByEmail(email);
        if(candidate!=null && candidate.getPassword().equals(password)) {
            return candidate.getId().toString();
        }
        throw null;
    }

    @Override
    public String applyJob(ApplyJobDTO jobRequest) {
        // TODO Auto-generated method stub
        try {
            Candidate candidate = candidateRepository.findById(jobRequest.getCandidateId()).orElseThrow(()->new EntityNotFoundException("Candidate not found"));
            Applied applied = new Applied();
            applied.setCandidate(candidate);
            applied.setJobId(jobRequest.getJobId());
            applied.setJobName(jobRequest.getCompanyName());
            applied.setStatus(jobRequest.getApplicationStatus());
            appliedRepository.save(applied);
            EnrollJobDTO enrollJobDTO = new EnrollJobDTO();
            enrollJobDTO.setCandidateId(jobRequest.getCandidateId());
            enrollJobDTO.setJobId(jobRequest.getJobId());
            enrollJobDTO.setCandidateName(candidate.getEmail());
            ResponseEntity<String> response = interviewClient.enrollInJob(enrollJobDTO);
            return "Successfully applied for job";
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<AppliedJobDTO> listOfAppliedJobs(Integer id) {
        // TODO Auto-generated method stub
        List<Applied> appliedJobs = appliedRepository.findByCandidateId(id);
        List<AppliedJobDTO> appliedJobDTOs = new ArrayList<>();
        for(Applied applied: appliedJobs) {
            CandidateJobDTO candidateJobDTO = interviewClient.getJobById(applied.getJobId()).getBody().get();

            appliedJobDTOs.add(new AppliedJobDTO(
                                applied.getId(),
                                applied.getCandidate().getId(),
                                applied.getJobId(),
                                applied.getJobName(),
                                candidateJobDTO.getRoleType(),
                                candidateJobDTO.getJobDescription(),
                                applied.getInterviewDate(),
                                applied.getStatus()
            ));
        }
        return appliedJobDTOs;
    }

    @Override
    public boolean updateAppliedJob(UpdateAppliedJobDTO updateAppliedJobDTO) {
        // TODO Auto-generated method stub
        int candidateId = updateAppliedJobDTO.getCandidateId();
        int jobId = updateAppliedJobDTO.getJobId();
        Date interviewDate = updateAppliedJobDTO.getInterviewDate();
        Optional<Applied> applied = appliedRepository.findByJobIdAndCandidateId(jobId, candidateId);
        if(applied.isPresent()) {
            Applied appliedJob = applied.get();
            appliedJob.setStatus("Interview Scheduled");
            applied.get().setInterviewDate(interviewDate);
            appliedRepository.save(applied.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTestScore(Integer id, Integer testScore) {
        // TODO Auto-generated method stub
        Optional<Applied> applied = appliedRepository.findById(id);
        if(applied.isPresent()) {
            appliedRepository.save(applied.get());
            return true;
        }
        return false;
    }

}
