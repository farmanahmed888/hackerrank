package com.hackerrank.interviewer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobEnrollmentInfoDTO {
    private int enrollId;
    private int candidateId;
    private String candidateName;
    private String testScore;
    private Date interviewDate;
    private boolean resultStatus;
    private String roomId;
}
