package com.task.tracker.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.questions.beans.QuestionBean;
import com.interview.questions.db.dao.QuestionDao;
import com.interview.questions.db.entities.QuestionModel;
import com.interview.questions.populator.Populator;
import com.task.tracker.services.QuestionService;
import com.task.tracker.services.TagService;

@Service
public class QuestionServiceImpl  implements QuestionService{
	
	@Autowired
	QuestionDao questionDao; 
	
	@Autowired
	Populator<QuestionBean, QuestionModel > populator;
	
	@Autowired
	TagService tagService;
	

	@Override
	public void saveQuestion(QuestionBean quesationBean) {
		
		QuestionModel questionModel = populator.populate(quesationBean);
		questionDao.save( questionModel);
		
		
	}


	@Override
	public List<QuestionBean> getQuesitonsList() {
		
		List<QuestionModel> allQuesiotns = questionDao.findAll();
		List<QuestionBean> beanslist = new ArrayList<QuestionBean>();
		
 		for(QuestionModel sa : allQuesiotns)
//		for(int i = 0 ; i < createTest.getQuestions().size();i++)
 		{
//			TestQuestionModel tq =  createTest.getQuestions().get(i).get;
 			QuestionBean bean = new QuestionBean();
 			bean.setQuestionTags(tagService.getTagsForIds(sa.getTags()));
 			bean.setQuestionText(sa.getQuestionText());
 			bean.setQuestionId(sa.getId().toHexString());
 			beanslist.add(bean);
 		}
 		
 		return beanslist;
	}

}
