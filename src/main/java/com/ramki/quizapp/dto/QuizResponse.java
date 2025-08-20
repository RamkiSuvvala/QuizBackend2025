package com.ramki.quizapp.dto;

import lombok.Data;

@Data
public class QuizResponse {

	private Integer questionId;
	private String selectedAnswer;
	public QuizResponse(Integer questionId, String selectedAnswer) {
		super();
		this.questionId = questionId;
		this.selectedAnswer = selectedAnswer;
	}
	
	
}
