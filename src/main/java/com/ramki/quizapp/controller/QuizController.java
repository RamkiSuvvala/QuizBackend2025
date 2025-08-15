package com.ramki.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramki.quizapp.entity.Question;
import com.ramki.quizapp.service.QuestionService;

@RestController
public class QuizController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/api/quiz")
	public ResponseEntity<List<Question>> getQuiz(@RequestParam String lang, @RequestParam String level) {
	    List<Question> allQuestions = null;
		allQuestions= questionService.getQuestions(lang,level);
	    return new ResponseEntity<>(allQuestions,HttpStatus.OK);
	}
	@GetMapping("/api/quiz/categories")
	public ResponseEntity<List<String>> getCategories() {
	    List<String> categories = questionService.findDistinctCategories();
	    return ResponseEntity.ok(categories);
	}
}