package com.microservices.quizService.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data

public class QuizDto {

	String categoryName;
	Integer numQ;
	String title;
}
