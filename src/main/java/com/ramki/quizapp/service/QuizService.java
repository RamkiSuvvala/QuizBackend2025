package com.ramki.quizapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ramki.quizapp.dao.QuestionDAO;
import com.ramki.quizapp.dao.QuestionsWrapper;
import com.ramki.quizapp.dao.QuizRepository;
import com.ramki.quizapp.dto.QuizResponse;
import com.ramki.quizapp.entity.Question;
import com.ramki.quizapp.entity.Quiz;

@Service
public class QuizService {

	@Autowired
	private QuestionDAO questionDAO;
	
	@Autowired
	QuizRepository quizRepo;

  
	
	public void createQuiz(String category, int noOfQues, String quizName) {
		List<Question> randomQuestionsByCategory = questionDAO.findRandomQuestionsByCategory(category, PageRequest.of(0, noOfQues));
		 
		Quiz quiz = new Quiz();
		
		quiz.setTitle(quizName);
		quiz.setQuestions(randomQuestionsByCategory);
		quizRepo.save(quiz);

	}

	public ResponseEntity<List<QuestionsWrapper>> getQuiz(int id) {
		Optional<Quiz> quizById = quizRepo.findById(id);
		List<Question> questions = quizById.get().getQuestions();
		List<QuestionsWrapper> listOFQuestions= questions.stream().map(question-> {
			QuestionsWrapper wrapper=new QuestionsWrapper();
			wrapper.setQuestionId(question.getQuestionId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setCategory(question.getCategory());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			return wrapper;
		}).collect( Collectors.toList());
		return new ResponseEntity<List<QuestionsWrapper>>(listOFQuestions,HttpStatus.OK);
	}

	public int calculateResults(int quizId, List<QuizResponse> quizResponse) {
		Optional<Quiz> quizById = quizRepo.findById(quizId);

		int results = quizById.map(quiz -> 
		    (int) quizResponse.stream()
		        .filter(response -> 
		            quiz.getQuestions().stream()
		                .anyMatch(question -> 
		                    question.getQuestionId() == response.getQuestionId() &&
		                    response.getSelectedAnswer().equals(question.getRightAnswer())
		                )
		        )
		        .count()
		).orElse(0);

		return results;
	}

}
