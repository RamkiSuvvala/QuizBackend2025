package com.ramki.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ramki.quizapp.dao.QuestionDAO;
import com.ramki.quizapp.entity.Question;

@Service
public class QuestionService {
	
	
	@Autowired
	QuestionDAO questionDAO;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDAO.findAll(),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Question> addQuestionOrUpdate(Question question) {
		try {
			return new ResponseEntity<Question>(questionDAO.save(question),HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<String> deleteQuestion(Integer questionId) {
		try {
			questionDAO.deleteById(questionId);
			return new ResponseEntity<String>("Sucess",HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed",HttpStatus.BAD_REQUEST);
	}
	public List<Question> getQuestions(String category,String level) {
        List<Question> questions = new ArrayList<>();

        questions=questionDAO.findByCategoryAndLevel(category,level);
        return questions;
    }

	public List<String> findDistinctCategories() {
		
		return questionDAO.findDistinctCategories();
	}

	public ResponseEntity<Question> getQuestionById(Integer questionId) {
		
		return questionDAO.findById(questionId)
		        .map(question -> ResponseEntity.ok(question)) // return 200 with the question
		        .orElseGet(() -> ResponseEntity.notFound().build()); // return 404 if not found
	}
}
