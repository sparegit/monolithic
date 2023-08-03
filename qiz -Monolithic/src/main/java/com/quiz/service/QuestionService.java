package com.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.Dao.QuestionDao;
import com.quiz.Model.Question;

@Service
public class QuestionService {
	@Autowired
	QuestionDao questionDao;
	
	public  ResponseEntity<List<Question>> getAllQuestions() {
		List<Question> result =  questionDao.findAll();
		return new ResponseEntity<List<Question>>(result,HttpStatus.OK);
	}

	public List<Question> getByCategory(String category) {
		
		return questionDao.findByCategory(category);
	}

	public Question createQuestion(Question question) {
		
		return questionDao.save(question);
	}

	
}
