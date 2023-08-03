package com.quiz.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quiz.Model.Question;

public interface QuestionDao extends JpaRepository<Question, Integer> {

	@Query(value ="SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :qnum", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int qnum);

	List<Question> findByCategory(String category);

}
