/**
 * 
 */
package com.ramki.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramki.quizapp.entity.Question;
import com.ramki.quizapp.service.QuestionService;

/**
 * 
 */
@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		
		return questionService.getAllQuestions();
	}
	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestionById(@PathVariable Integer questionId) {
		return questionService.getQuestionById(questionId);
	}
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) {
		
		return questionService.getAllQuestionsByCategory(category);
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		
		return questionService.addQuestionOrUpdate(question);
	}
	@PutMapping("updateQuestion")
	public ResponseEntity<Question> updatedQuestion(@RequestBody Question question) {
		return questionService.addQuestionOrUpdate(question);
	}
	@DeleteMapping("deleteQuestion/{questionId}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer questionId) {
		return questionService.deleteQuestion(questionId);
	}

}
