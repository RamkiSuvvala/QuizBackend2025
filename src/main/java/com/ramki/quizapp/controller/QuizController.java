package com.ramki.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramki.quizapp.dao.QuestionsWrapper;
import com.ramki.quizapp.dto.QuizResponse;
import com.ramki.quizapp.entity.Question;
import com.ramki.quizapp.service.QuestionService;
import com.ramki.quizapp.service.QuizService;

@RestController
public class QuizController {

	@Autowired
	QuestionService questionService;
	@Autowired
	QuizService quizService;

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
	@PostMapping("/api/manage/quiz/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int noOfQues,@RequestParam String quizName){
		quizService.createQuiz(category,noOfQues,quizName);
		
		return ResponseEntity.ok("success");
	}
	@GetMapping("/api/quiz/get/{id}")
	public ResponseEntity<List<QuestionsWrapper>> getQuiz(@PathVariable int id){
		return quizService.getQuiz(id);
		
	}
	@PostMapping("api/quiz/submit/{quizId}")
	public ResponseEntity<String> submitResults(@PathVariable int quizId, @RequestBody List<QuizResponse> quizResponse){
		int results=0;
		results=quizService.calculateResults(quizId,quizResponse);
		return ResponseEntity.ok(" Your Scope : "+ results+"/"+quizResponse.size());
	}
}