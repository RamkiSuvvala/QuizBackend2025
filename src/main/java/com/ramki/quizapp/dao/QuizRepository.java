package com.ramki.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramki.quizapp.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
