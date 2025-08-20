/**
 * 
 */
package com.ramki.quizapp.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ramki.quizapp.entity.Question;

/**
 * 
 */
@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);

	List<Question> findByCategoryAndLevel(String category, String level);

	@Query("SELECT DISTINCT q.category FROM Question q ORDER BY q.category")
	List<String> findDistinctCategories();

	@Query(value = "SELECT * FROM questions WHERE category = :category ORDER BY RAND()", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(@Param("category") String category, Pageable pageable);
;
	
}
