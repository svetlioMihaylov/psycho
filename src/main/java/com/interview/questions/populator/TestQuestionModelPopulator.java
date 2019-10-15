package com.interview.questions.populator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interview.questions.db.entities.QuestionModel;
import com.interview.questions.db.entities.TestQuestionModel;
import com.interview.questions.enums.QuestionType;
import com.task.tracker.services.TagService;

@Component
public class TestQuestionModelPopulator implements Populator<QuestionModel, TestQuestionModel>{
	
	@Autowired
	TagService tagService;

	@Override
	public TestQuestionModel populate(QuestionModel questionModel) {
		TestQuestionModel testQuestion = new TestQuestionModel();
		testQuestion.setQuestionText(questionModel.getQuestionText());
		testQuestion.setQuestionType(questionModel.getQuestionType());
		testQuestion. setTags(tagService.getTagsForIds(questionModel.getTags()));
		if(QuestionType.MULTIPLE_CHOICE.equals(questionModel.getQuestionType()))
		{
			Map<String, Boolean> answersMap = new HashMap<>();
			for(String answer : questionModel.getAnswersList())
			{
				answersMap.put(answer, false);
			}

		}
		return testQuestion;
	}

}
