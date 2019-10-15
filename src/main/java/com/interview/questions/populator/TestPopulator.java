package com.interview.questions.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interview.questions.beans.TestBean;
import com.interview.questions.db.dao.UserModelDao;
import com.interview.questions.db.entities.TagModel;
import com.interview.questions.db.entities.TestModel;
import com.interview.questions.db.entities.UserModel;
import com.task.tracker.services.TagService;

@Component
public class TestPopulator implements Populator<TestBean, TestModel > {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	UserModelDao userModelDao;

	@Override
	public TestModel populate(TestBean bean) {
		
		TestModel  testModel = new TestModel();
		testModel.setCandidateName(bean.getCandidateName());
		testModel.setCandidateMail(bean.getCandidateMail());
		testModel.setNumberOfQuestions(bean.getNumberOfQuestions());
		
		for(String tagName : bean.getQuestionTags())
		{
			TagModel tagModel = tagService.findTagByName(tagName);
			if(null != tagModel)
			{
				testModel.getTestTags().add(tagModel.get_id());
			}
		}
		
		for(String aa : bean.getReviewers())
		{
			UserModel userModel= userModelDao.findByEmail(aa);
			if(null != userModel)
			{
				testModel.getReviers().add(userModel.getId());
			}
		}
		
		return testModel;
	}

}
