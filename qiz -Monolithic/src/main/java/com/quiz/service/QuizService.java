package com.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.quiz.Dao.QuestionDao;
import com.quiz.Dao.QuizDao;
import com.quiz.Model.Question;
import com.quiz.Model.QuestionWrapper;
import com.quiz.Model.Quiz;
import com.quiz.Model.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;
	public ResponseEntity<String> createQuiz( String category, int qnum, String title) {
		
		List<Question> questions= questionDao.findRandomQuestionsByCategory(category, qnum);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
	Optional<Quiz> quiz =	quizDao.findById(id);
	List<Question> questionfromdb = quiz.get().getQuestions();
	List<QuestionWrapper> questionsforuser = new ArrayList<>();
	for(Question q : questionfromdb) {
		QuestionWrapper qWrapper = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
		questionsforuser.add(qWrapper);
	}
	
	
		return new ResponseEntity<>(questionsforuser,HttpStatus.OK);
	}
	public ResponseEntity<Integer> sayTheScore(Integer id, List<Response> responses) {
		Optional<Quiz> quiz =quizDao.findById(id);
		List<Question> questions = quiz.get().getQuestions();
		int result=0;
		for(int i=0;i<responses.size();i++) {
			if(responses.get(i).equals(questions.get(i).getRightAns())) {
				result++;
			}
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	
	
	
	
	

	
}
