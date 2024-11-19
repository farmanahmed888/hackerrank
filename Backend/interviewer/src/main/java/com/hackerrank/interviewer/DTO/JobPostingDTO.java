package com.hackerrank.interviewer.DTO;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingDTO {
    private String company;
    private String contact;
    private String jobDescription;
    private String status = "open";
    private int noOfEnrollments = 0;
    private String roleType;
    private int interviewerId;
    private int experience;
    private Set<String> requirements = new HashSet<>();
}




