package org.java.project.api.controller.dto;

import org.java.project.pojo.Question;

public class QuestionDto {
	Question question;
	
	public QuestionDto(Question question) {
		setQuestion(question);
	}

	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
