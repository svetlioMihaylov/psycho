package com.interview.questions.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interview.questions.beans.TestBean;
import com.interview.questions.db.entities.TestModel;
import com.task.tracker.services.TagService;


@Component
public class TestBeanPopulator implements Populator<TestModel, TestBean>{

	
	@Autowired
	TagService tagService;
	
	@Override
	public TestBean populate(TestModel testModel) {

		TestBean bean = new TestBean();
		bean.setCandidateName(testModel.getCandidateName());
		bean.setCandidateMail(testModel.getCandidateMail());
		bean.setQuestionTags(tagService.getTagsForIds(testModel.getTestTags()));
		bean.setNumberOfQuestions(testModel.getNumberOfQuestions());
		return bean;
	}

}
