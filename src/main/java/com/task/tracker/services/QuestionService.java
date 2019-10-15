package com.task.tracker.services;

import java.util.List;

import com.interview.questions.beans.QuestionBean;

public interface QuestionService {
	
	public void saveQuestion(QuestionBean quesationModel);
	
	public List<QuestionBean> getQuesitonsList();

}
