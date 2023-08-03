package com.quiz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.Model.QuestionWrapper;
import com.quiz.Model.Quiz;
import com.quiz.Model.Response;
import com.quiz.service.QuizService;



@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	
		@PostMapping("/create")
		public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int qnum, @RequestParam String Title) {
			
			return quizService.createQuiz(category,qnum,Title);
		}
		
		@GetMapping("/get/{id}")
		public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
			
			return quizService.getQuiz(id);
		}
		
		@PostMapping("/submit{id}")
		public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
			return quizService.sayTheScore(id,  responses);
		}
	
	
}
