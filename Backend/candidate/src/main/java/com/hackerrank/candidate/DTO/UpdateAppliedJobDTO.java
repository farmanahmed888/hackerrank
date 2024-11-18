package com.hackerrank.candidate.DTO;

import java.sql.Date;

import lombok.Data;
@Data
public class UpdateAppliedJobDTO {
    Integer candidateId;
    Integer jobId;
    Date interviewDate;
}
