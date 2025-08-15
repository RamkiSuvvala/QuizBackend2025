package com.ramki.quizapp;
import java.util.List;

import com.ramki.quizapp.entity.Question;

public class QuizResponse {
    private List<Question> questions;

    // Constructors
    public QuizResponse() {}

    public QuizResponse(List<Question> questions) {
        this.questions = questions;
    }

    // Getters and setters
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
