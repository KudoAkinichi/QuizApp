package com.microservices.quizService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.quizService.model.Quiz;


public interface QuizRepo extends MongoRepository<Quiz,String>{

}
