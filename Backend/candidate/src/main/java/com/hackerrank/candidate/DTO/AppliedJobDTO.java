package com.hackerrank.candidate.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppliedJobDTO {
    private Integer id;
    private Integer candidateId;
    private Integer jobId;
    private String jobName;
    private String jobRole;
    private String jobDescription;
    private Date interviewDate;
    private String status="Pending";
}
