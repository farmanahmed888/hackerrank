package com.hackerrank.interviewer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.interviewer.Entities.Question;

public interface  QuestionRepository extends JpaRepository<Question, Long>{

}
