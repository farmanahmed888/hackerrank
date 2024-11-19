package com.hackerrank.interviewer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.interviewer.Entities.Requirements;
import java.util.List;
import java.util.Optional;

public interface  RequirementRepository extends JpaRepository<Requirements, Integer> {
    Optional<Requirements> findByRequirementName(String requirementName);
    List<Requirements> findByRequirementNameIn(List<String> fullfilledRequirements);
}
