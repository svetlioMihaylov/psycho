package com.interview.questions.beans;

import java.io.Serializable;
import java.util.List;

public class QuestionAnswerBean implements Serializable{

	private static final long serialVersionUID = -5377975932396200077L;
	
	private int number;
	
	private String answer;
	
	private List<String> multipleChoise;
	
	public List<String> getMultipleChoise() {
		return multipleChoise;
	}

	public void setMultipleChoise(List<String> multipleChoise) {
		this.multipleChoise = multipleChoise;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
