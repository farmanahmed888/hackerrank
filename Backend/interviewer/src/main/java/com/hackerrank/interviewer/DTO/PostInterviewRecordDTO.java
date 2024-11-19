package com.hackerrank.interviewer.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostInterviewRecordDTO {
    private Long jobId;
    private Long candidateId;
    private String positiveFeedback;
    private String negativeFeedback;
    private List<String> fullfilledRequirements;
}
