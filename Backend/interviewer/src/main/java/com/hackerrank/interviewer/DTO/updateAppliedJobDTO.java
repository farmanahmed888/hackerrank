package com.hackerrank.interviewer.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class updateAppliedJobDTO {
    int candidateId;
    int jobId;
    Date InterviewDate;
}
