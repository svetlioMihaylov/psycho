package com.interview.questions.beans;

import com.interview.questions.enums.Answers;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class QuestionBean  implements Serializable{
	
	private static final long serialVersionUID = -9124755492958071889L;
	
	private int number;
	
	private String questionId;

	private String questionText;
	
	private List<String> questionTags;



	private List<Answers> availableAnswers;

	private Answers givenAnswer;
	

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<String> getQuestionTags() {
		return questionTags;
	}

	public void setQuestionTags(List<String> questionTags) {
		this.questionTags = questionTags;
	}


	public List<Answers> getAvailableAnswers() {
		return availableAnswers;
	}

	public void setAvailableAnswers(List<Answers> availableAnswers) {
		this.availableAnswers = availableAnswers;
	}

	public Answers getGivenAnswer() {
		return givenAnswer;
	}

	public void setGivenAnswer(Answers givenAnswer) {
		this.givenAnswer = givenAnswer;
	}


//	public List<String> getMultipleChoiseanswers() {
//		return multipleChoiseanswers;
//	}
//
//	public void setMultipleChoiseanswers(List<String> multipleChoiseanswers) {
//		this.multipleChoiseanswers = multipleChoiseanswers;
//	}
//
//	private List<String> multipleChoiseanswers;
	
	

}
