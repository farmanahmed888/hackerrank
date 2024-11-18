package com.hackerrank.candidate.DTO;

import java.util.List;

import lombok.Data;
@Data
public class CandidateJobDTO {
    private String roleType;
    private String jobDescription;
    private List<String> requirements;
}
