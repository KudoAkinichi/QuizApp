package com.microservices.quizService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.quizService.model.QuestionWrapper;
import com.microservices.quizService.model.Quiz;
import com.microservices.quizService.model.QuizDto;
import com.microservices.quizService.model.Response;
import com.microservices.quizService.service.QuizService;


@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizdto){
		return quizService.createQuiz(quizdto.getCategoryName(),quizdto.getNumQ(),quizdto.getTitle());
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
