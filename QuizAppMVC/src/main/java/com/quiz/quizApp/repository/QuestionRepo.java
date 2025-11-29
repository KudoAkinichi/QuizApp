package com.quiz.quizApp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.quiz.quizApp.model.Question;

public interface QuestionRepo extends MongoRepository<Question,String>{

	List<Question> findByCategory(String category);

	@Aggregation(pipeline = {
	        "{ $match: { category: ?0 } }",
	        "{ $sample: { size: ?1 } }"
	    })
	List<Question> findRandomQuestionByCategory(String category, int numQ);

}
