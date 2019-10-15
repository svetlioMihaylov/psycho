package com.interview.questions.db.entities;

import java.util.Date;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class CommentModel {
	
	String commentText;
	
	String author;
	
	Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

}
