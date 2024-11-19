package com.hackerrank.interviewer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTestScoreDTO {
    private int jobId;
    private int candidateId;
    private String TestScore;
}
