package com.interview.questions.db.entities;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("tests")
public class TestModel {
	
	public TestModel() {
		testTags = new ArrayList<ObjectId>();
		reviers = new ArrayList<ObjectId>();
		questions = new ArrayList<TestQuestionModel>();
	}
	
	@Id
	private ObjectId _id;
	
	private String candidateName;
	
	@Email
	private String candidateMail;
	
	private int numberOfQuestions;
	
	private  List<ObjectId> testTags;
	
	private List<ObjectId> reviers;
	
	private List<TestQuestionModel> questions;
	
	private CommentModel commentModel;
	
	private boolean completed;
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public CommentModel getCommentModel() {
		return commentModel;
	}

	public void setCommentModel(CommentModel commentModel) {
		this.commentModel = commentModel;
	}

	public List<TestQuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(List<TestQuestionModel> questions) {
		this.questions = questions;
	}

	public List<ObjectId> getTestTags() {
		return testTags;
	}

	public void setTestTags(List<ObjectId> testTags) {
		this.testTags = testTags;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateMail() {
		return candidateMail;
	}

	public void setCandidateMail(String candidateMail) {
		this.candidateMail = candidateMail;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}


	public List<ObjectId> getReviers() {
		return reviers;
	}

	public void setReviers(List<ObjectId> reviers) {
		this.reviers = reviers;
	}
	
	

}
