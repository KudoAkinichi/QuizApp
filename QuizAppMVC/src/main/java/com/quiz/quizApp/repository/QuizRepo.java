package com.quiz.quizApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.quiz.quizApp.model.Quiz;

public interface QuizRepo extends MongoRepository<Quiz,String>{

}
