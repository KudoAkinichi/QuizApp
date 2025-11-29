package com.quiz.quizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizApp.model.QuestionWrapper;
import com.quiz.quizApp.model.Quiz;
import com.quiz.quizApp.model.Response;
import com.quiz.quizApp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
		return quizService.createQuiz(category,numQ,title);
	}
	
	@GetMapping("get/{quizId}")
	public ResponseEntity<List<QuestionWrapper>> createQuiz(@PathVariable String quizId){
		return quizService.getQuiz(quizId);
	}
	
	@PostMapping("submit/{quizId}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable String quizId,@RequestBody List<Response> responses){
		return quizService.submitQuiz(quizId,responses);
	}
}
