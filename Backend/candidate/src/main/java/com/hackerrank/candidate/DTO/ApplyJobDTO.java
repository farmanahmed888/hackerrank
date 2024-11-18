package com.hackerrank.candidate.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplyJobDTO {
    private Integer candidateId;
    private Integer jobId;
    private String companyName;
    private String testScore;
    private String applicationStatus="Pending";
}
