package com.interview.questions.populator;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.interview.questions.enums.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interview.questions.beans.QuestionBean;
import com.interview.questions.db.entities.QuestionModel;
import com.interview.questions.db.entities.TagModel;
import com.interview.questions.enums.QuestionType;
import com.task.tracker.services.TagService;

@Component
public class QuestionPopulator implements Populator<QuestionBean, QuestionModel > {
	
	@Autowired
	TagService tagService;
	

	@Override
	public QuestionModel populate(QuestionBean bean) {
		
		QuestionModel questionModel = new QuestionModel();
		questionModel.setQuestionText(bean.getQuestionText());
		for(String tag : bean.getQuestionTags())
		{
			TagModel tagModel = tagService.findTagByName(tag);
			if(null != tagModel)
			{
				questionModel.getTags().add(tagModel.get_id());
			}
		}

		questionModel.setAvailableAnswers(new ArrayList(EnumSet.allOf(Answers.class)));

		return questionModel;
	}
	


}
