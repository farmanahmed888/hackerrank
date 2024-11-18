package com.hackerrank.candidate.Entities;

import java.sql.Date;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Applied {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id",referencedColumnName = "id")
    private Candidate candidate;
    private Integer jobId;
    private String jobName;
    private Date interviewDate;
    private String status;
}
