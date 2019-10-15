package com.interview.questions.beans;

import java.io.Serializable;

public class CommentBean implements Serializable {

	private static final long serialVersionUID = 2295508847711508349L;

	String commentText;

	String author;

	String date;

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


}
