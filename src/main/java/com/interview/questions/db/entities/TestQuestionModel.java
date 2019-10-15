package com.interview.questions.db.entities;

import java.util.ArrayList;
import java.util.List;

import com.interview.questions.enums.Answers;
import org.mongodb.morphia.annotations.Embedded;

import com.interview.questions.enums.QuestionType;

@Embedded
public class TestQuestionModel {
	
	public TestQuestionModel() {
		tags = new ArrayList<String>();
	}
	
	private int number;
	private String questionText;
	
	private QuestionType questionType;


	private List<Answers> possibleAnswers;

	private Answers selectedAnswer;

	private List<String> tags;
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	List<CommentModel> comments;

	public String getQuestionText() {
		return questionText;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}


	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}

	public List<Answers> getPossibleAnswers() {
		return possibleAnswers;
	}

	public void setPossibleAnswers(List<Answers> possibleAnswers) {
		this.possibleAnswers = possibleAnswers;
	}

	public Answers getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Answers selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

}
