package com.quiz.quizApp.model;

import org.springframework.data.annotation.Id;

public class Response {
	@Id
    private String id;
	private String rightAnswer;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
}
