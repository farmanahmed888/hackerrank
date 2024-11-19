package com.hackerrank.interviewer.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    private String company;
    private String contact;
    private String jobDescription;
    private String status;
    private int numberOfEnrollments;
    private String roleType;

    @ManyToOne
    @JoinColumn(name="interviewerid")
    private Interviewer interviewer;  
    @ManyToMany
    @JoinTable(name = "job_questions",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")) 
    private Set<Question> questions=new HashSet<Question>();
    @ManyToMany
    @JoinTable(name = "job_requirements",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id"))
    private Set<Requirements> allRequirements=new HashSet<Requirements>();
}
