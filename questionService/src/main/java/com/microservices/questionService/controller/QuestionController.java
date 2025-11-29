package com.microservices.questionService.controller;

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

import com.microservices.questionService.model.Question;
import com.microservices.questionService.model.QuestionWrapper;
import com.microservices.questionService.model.Response;
import com.microservices.questionService.service.QuestionService;


@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getQuestions() {
		return questionService.getAllQuestions();
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		return questionService.getQuestionByCategory(category);
	}
	
	//generate (return list of questionId
	@GetMapping("generate")
	public ResponseEntity<List<String>> getQuestionForQuiz(@RequestParam String category,@RequestParam Integer numQ){
		return questionService.getQuestionForQuiz(category,numQ);
	}
	
	//getQuestion(questionId)
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<String> questionIds){
		return questionService.getQuestions(questionIds);
	}
	
	//calculateScore
	@PostMapping("score")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.calculateScore(responses);
	}
	

}
