package com.quiz.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.Model.Question;
import com.quiz.advice.TrackTime;
import com.quiz.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	@GetMapping("all")
	@TrackTime
	public ResponseEntity<List<Question>> getAllQuestions(){
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public List<Question> getByCategory(@PathVariable String category){
		return questionService.getByCategory(category);
	}
	
	@PostMapping("add")
	public Question createQuestion(@RequestBody Question question) {
		return questionService.createQuestion(question);
	}
	
}
