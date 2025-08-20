package com.ramki.quizapp.dao;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class QuestionsWrapper {

	
	private Integer questionId;

    private String category;
    private String questionTitle;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

}
