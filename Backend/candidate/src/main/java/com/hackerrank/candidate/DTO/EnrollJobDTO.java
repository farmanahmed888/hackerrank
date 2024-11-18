package com.hackerrank.candidate.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollJobDTO {
    private Integer jobId;
    private Integer candidateId;
    private String candidateName;
}
