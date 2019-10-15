package com.task.tracker.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.interview.questions.beans.AddCommentBean;
import com.interview.questions.beans.QuestionBean;
import com.interview.questions.beans.TestBean;
import com.interview.questions.beans.TestValidationBean;
import com.interview.questions.beans.UpdateQuestionBean;
import com.interview.questions.db.entities.TestModel;
import com.interview.questions.db.entities.TestQuestionModel;
import com.interview.questions.populator.EntryImpl;


public interface TestService {
	
	public TestModel createTest(TestBean testBean);
	
	public List<TestQuestionModel> generateQuestions(TestModel testBean);
	
	public List<QuestionBean> getBeans(TestBean testBean, HttpSession session);

	public TestModel findTestById(String _id);
	
	public void updateQuestion(UpdateQuestionBean updateQuestionBean, HttpSession session);
	
	public TestModel findTestForCandidate(TestValidationBean bean);
	
	public void save(TestModel testModel);
	
	public TestBean populateBean(TestModel test);
	
	public QuestionBean getQuestionBean(int questionId, HttpSession session);
	
	public void addComment(TestModel testModel, AddCommentBean bean, String mail);
	
	public List <EntryImpl<String, String>> getTestForReview(String mail);
	
}


