package com.interview.questions.beans;

import java.io.Serializable;

public class AddCommentBean implements Serializable{
	
	private static final long serialVersionUID = -6175718264526097056L;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getNumberId() {
		return numberId;
	}
	public void setNumberId(int numberId) {
		this.numberId = numberId;
	}
	String comment;
	int numberId;

}
