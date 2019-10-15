package com.interview.questions.db.entities;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class MultipleChoiceAnswerModel {
	
	private List<String > answers ;

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

}
