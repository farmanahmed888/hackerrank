package com.hackerrank.interviewer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobEnrollDTO {
    private int jobId;
    private int candidateId;
    private String candidateName;
}
