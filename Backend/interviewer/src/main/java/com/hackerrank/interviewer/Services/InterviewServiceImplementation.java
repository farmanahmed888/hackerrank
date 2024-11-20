package com.hackerrank.interviewer.Services;

import com.hackerrank.interviewer.DTO.*;
import com.hackerrank.interviewer.Entities.*;
import com.hackerrank.interviewer.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImplementation implements InterviewService {
    private final CandidateClient candidateClient;
    @Autowired
    InterviewRepository interviewerRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    RequirementRepository allRequirementsRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    private InterviewRecordRepository interviewRecordRepository;

    public InterviewServiceImplementation(CandidateClient candidateClient) {
        this.candidateClient = candidateClient;
    }

    private String generateString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            if (i > 0 && i % 4 == 0) {
                stringBuilder.append("-");
            }
            // generate a random character
            char randomChar = (char) ('a' + Math.random() * ('z' - 'a' + 1));
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
    private QuestionDTO mapQuestionToQuestionDTO(Question question) {
        return new QuestionDTO(
                question.getId(),
                question.getQuestion(),
                question.getTopic(),
                question.getQuestionName(),
                question.getTestCases()
        );
    }

    private JobInfoDTO mapJobToJobInfoDTO(Job job) {
        List<String> requirements = job.getAllRequirements().stream()
                .map(Requirements::getRequirementName)
                .collect(Collectors.toList());

        List<QuestionDTO> questions = job.getQuestions().stream()
                .map(this::mapQuestionToQuestionDTO)
                .collect(Collectors.toList());

        return new JobInfoDTO(
                job.getId(),
                job.getCompany(),
                job.getJobDescription(),
                job.getStatus(),
                job.getNumberOfEnrollments(),
                job.getRoleType(),
                job.getInterviewer().getId(),
                requirements,
                questions
        );
    }

    private JobEnrollmentInfoDTO mapToJobEnrollmentInfoDTO(Enrollment enrollment) {
        JobEnrollmentInfoDTO jobEnrollmentInfoDTO = new JobEnrollmentInfoDTO();
        jobEnrollmentInfoDTO.setCandidateId(enrollment.getCandidateId());
        jobEnrollmentInfoDTO.setCandidateName(enrollment.getCandidateName());
        jobEnrollmentInfoDTO.setInterviewDate(enrollment.getInterviewDate());
        jobEnrollmentInfoDTO.setRoomId(enrollment.getRoomId());
        jobEnrollmentInfoDTO.setEnrollId(enrollment.getId());
        return jobEnrollmentInfoDTO;
    }


    @Override
    public String login(String email, String password) {
        Interviewer candidate = interviewerRepository.findByEmail(email);
        if (candidate != null && candidate.getPassword().equals(password)) {
            return candidate.getId().toString();
        }
        return null;
    }

    @Override
    public int activeJobsCount(int id) {
        return jobRepository.countAllByInterviewerId(id);
    }

    @Override
    public List<JobInfoDTO> getClosedJobs(int id) {
        List<Job> jobs = jobRepository.findByInterviewerId(id);
        return jobs.stream()
                .filter(job -> job.getStatus().equals("closed"))
                .map(this::mapJobToJobInfoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Job> allactiveJobsCount() {
        return jobRepository.findByStatus("open");
    }

    @Override
    public String createJob(JobPostingDTO jobPostingRequest) {
        Job job = new Job();
        job.setCompany(jobPostingRequest.getCompany());
        job.setContact(jobPostingRequest.getContact());
        job.setJobDescription(jobPostingRequest.getJobDescription());
        job.setStatus(jobPostingRequest.getStatus());
        job.setRoleType(jobPostingRequest.getRoleType());
        Optional<Interviewer> interviewerOptional = interviewerRepository.findById(jobPostingRequest.getInterviewerId());
        if (interviewerOptional.isPresent()) {
            job.setInterviewer(interviewerOptional.get());
            Set<Requirements> allRequirements = new HashSet<>();
            for (String requirementName : jobPostingRequest.getRequirements()) {
                Optional<Requirements> requirementOptional = allRequirementsRepository.findByRequirementName(requirementName);
                requirementOptional.ifPresent(allRequirements::add);
            }
            job.setAllRequirements(allRequirements);
            jobRepository.save(job);
            return "Job created successfully";
        }
        return "Interviewer not found for the given ID";
    }

    @Override
    public List<JobInfoDTO> getJobs(int id) {
        List<Job> jobs = jobRepository.findByInterviewerId(id);
        return jobs.stream()
                .filter(job -> job.getStatus().equals("open"))
                .map(this::mapJobToJobInfoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobEnrollmentInfoDTO> getJobEnrollments(Integer jobId) {
        List<Enrollment> enrollments = enrollmentRepository.findByJobId(jobId);
        List<JobEnrollmentInfoDTO> jobEnrollmentInfoDTOs = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            JobEnrollmentInfoDTO dto = mapToJobEnrollmentInfoDTO(enrollment);
            if(interviewRecordRepository.findByEnrollment(enrollment)!=null)
                dto.setResultStatus(true);

            jobEnrollmentInfoDTOs.add(dto);
        }
        return jobEnrollmentInfoDTOs;
    }

    @Override
    public void scheduleInterview(ScheduleInterviewDTO dto) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(dto.getEnrollId());
        if (enrollment.isEmpty()) {
            return;
        }
        Enrollment enrollmentDate = enrollment.get();
        enrollmentDate.setInterviewDate(dto.getInterviewDate());
        enrollmentDate.setRoomId(generateString());
        enrollmentRepository.save(enrollmentDate);

        updateAppliedJobDTO updateAppliedJobDTO = new updateAppliedJobDTO();
        updateAppliedJobDTO.setCandidateId(enrollmentDate.getCandidateId());
        updateAppliedJobDTO.setJobId(enrollmentDate.getId());
        updateAppliedJobDTO.setInterviewDate(dto.getInterviewDate());
        candidateClient.updateAppliedJob(updateAppliedJobDTO);
    }

    @Override
    public CountDTO counter(int id) {
        CountDTO dto = new CountDTO();
        dto.setActiveJobs(jobRepository.findByStatus("open").size());
        dto.setClosedJobs(jobRepository.findByStatus("closed").size());
        return dto;
    }

    @Override
    public void interviewResult(InterviewRecordInfoDTO dto) {
        List<Enrollment> enroll = enrollmentRepository.findByRoomId(dto.getRoomId());
        if(enroll!=null && enroll.size()==1){
            InterviewerRecord result = new InterviewerRecord();
            result.setCandidateId(enroll.get(0).getCandidateId());
            result.setEnrollment(enroll.get(0));
            result.setPositiveFeedback(dto.getPositiveFeedback());
            result.setNegativeFeedback(dto.getNegativeFeedback());
            interviewRecordRepository.save(result);
        }
    }

    @Override
    public boolean enrollInJob(JobEnrollDTO jobEnrollRequest) {
        try {
            Optional<Job> optionalJob = jobRepository.findById(jobEnrollRequest.getJobId());
            if (optionalJob.isPresent()) {
                Job job = optionalJob.get();
                job.setNumberOfEnrollments(job.getNumberOfEnrollments() + 1);
                jobRepository.save(job);
                Enrollment enrollment = new Enrollment();
                enrollment.setCandidateId(jobEnrollRequest.getCandidateId());
                enrollment.setJob(job);
                enrollment.setCandidateName(jobEnrollRequest.getCandidateName());
                enrollmentRepository.save(enrollment);

                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void closeJob(int jobId) {
        if(jobRepository.findById(jobId).isEmpty()){
            return;
        }
        Job job = jobRepository.findById(jobId).get();
        job.setStatus("Closed");
        jobRepository.save(job);
    }

    @Override
    public boolean updateTestScore(UpdateTestScoreDTO updateTestScoreRequest) {
        try {
            Optional<Enrollment> optionalEnrollment = enrollmentRepository.findByCandidateIdAndJobId(
            updateTestScoreRequest.getCandidateId(),
            updateTestScoreRequest.getJobId());
            if (optionalEnrollment.isPresent()) {
                Enrollment enrollment = optionalEnrollment.get();
                enrollmentRepository.save(enrollment);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<JobForCandidateDTO> getJob(int id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            Job presentJob = job.get();
            JobForCandidateDTO jobdto = new JobForCandidateDTO();
            jobdto.setJobDescription(presentJob.getJobDescription());
            Set<Requirements> requirements = presentJob.getAllRequirements();
            List<String> req = new ArrayList<>();
            for (Requirements it : requirements) {
                req.add(it.getRequirementName());
            }
            jobdto.setRequirements(req);
            jobdto.setRoleType(presentJob.getRoleType());

            return Optional.of(jobdto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean candidateCodeEditorCheck(CandidateCheckDTO CandidateCheckDTO) {
        Enrollment enroll = enrollmentRepository.findByCandidateNameandRoomId(CandidateCheckDTO.getCandidateName(), CandidateCheckDTO.getRoomId());
        return enroll != null;
    }

    @Override
    public boolean interviewerCodeEditorCheck(String interviewerEmail) {
        Interviewer interviewer = interviewerRepository.findByEmail(interviewerEmail);
        return interviewer != null;
    }

    @Override
    public InterviewerRecord checkResults(int enrollId) {
        Optional<Enrollment> enroll = enrollmentRepository.findById(enrollId);
        return enroll.map(enrollment -> (interviewRecordRepository.findByEnrollment(enrollment))).orElse(null);
    }
}
