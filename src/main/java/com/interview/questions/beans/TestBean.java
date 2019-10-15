package com.interview.questions.beans;

import java.io.Serializable;
import java.util.List;

public class TestBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3275545354980433076L;

	private String candidateName;
	
	private String candidateMail;
	
	private int numberOfQuestions;
	
	private List<String> questionTags;
	
	private List<String> reviewers;
	
	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public String getCandidateMail() {
		return candidateMail;
	}

	public void setCandidateMail(String candidateMail) {
		this.candidateMail = candidateMail;
	}

	public List<String> getQuestionTags() {
		return questionTags;
	}

	public void setQuestionTags(List<String> questionTags) {
		this.questionTags = questionTags;
	}

	public List<String> getReviewers() {
		return reviewers;
	}

	public void setReviewers(List<String> reviewers) {
		this.reviewers = reviewers;
	}


}
