package com.hackerrank.interviewer.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JobForCandidateDTO {
private String roleType;
private String jobDescription;
private List<String> requirements = new ArrayList<>();
}
