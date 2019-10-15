package com.interview.questions.db.entities;

import java.util.ArrayList;
import java.util.List;

import com.interview.questions.enums.Answers;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.interview.questions.enums.QuestionType;

@Entity("questions")
public class QuestionModel {
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	@Id
	private ObjectId id;
	
	private String questionText;
	private QuestionType questionType;
	private List<String> answersList;


	private List<Answers> availableAnswers;
	
	@Reference
	private TagModel as;
	

	public TagModel getAs() {
		return as;
	}

	public void setAs(TagModel as) {
		this.as = as;
	}

	private List<ObjectId> tags;
	
	public QuestionModel()
	{
		tags = new ArrayList<ObjectId>();
	}
	
	public String getQuestionText() {
		return questionText;
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


	public List<ObjectId> getTags() {
		return tags;
	}

	public void setTags(List<ObjectId> tags) {
		this.tags = tags;
	}

	public List<String> getAnswersList() {
		return answersList;
	}
	
	public void setAnswersList(List<String> answersList) {
		this.answersList = answersList;
	}


	public List<Answers> getAvailableAnswers() {
		return availableAnswers;
	}

	public void setAvailableAnswers(List<Answers> availableAnswers) {
		this.availableAnswers = availableAnswers;
	}
}
