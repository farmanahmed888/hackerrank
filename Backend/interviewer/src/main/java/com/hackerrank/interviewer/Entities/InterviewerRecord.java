package com.hackerrank.interviewer.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewerRecord {
    @Id
    private int id;
    private int candidateId;
    @OneToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;
    private String positiveFeedback;
    private String negativeFeedback;
}
