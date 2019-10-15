package com.interview.questions.beans;

import com.interview.questions.enums.Answers;

import java.io.Serializable;
import java.util.Map;

public class TestQuestionBean  implements Serializable{

	private static final long serialVersionUID = 195803295280831033L;

	private Answers questionAnswer;
	
	private int number;

	
	private int questionCount;
	
	private String qustionText;



	public String getQustionText() {
		return qustionText;
	}

	public void setQustionText(String qustionText) {
		this.qustionText = qustionText;
	}

	public Answers getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(Answers questionAnswer) {
		this.questionAnswer = questionAnswer;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

}
