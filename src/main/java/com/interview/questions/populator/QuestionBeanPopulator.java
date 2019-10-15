package com.interview.questions.populator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.interview.questions.beans.CommentBean;
import com.interview.questions.beans.QuestionBean;
import com.interview.questions.db.entities.CommentModel;
import com.interview.questions.db.entities.TestQuestionModel;
import com.interview.questions.enums.QuestionType;

@Component
public class QuestionBeanPopulator implements Populator<TestQuestionModel, QuestionBean> {

	@Override
	public QuestionBean populate(TestQuestionModel quesitonModel) {

		QuestionBean questionBean = new QuestionBean();

		questionBean.setQuestionText(quesitonModel.getQuestionText());

		if (null != quesitonModel.getComments()) {
			List<CommentBean> commnts = new ArrayList<CommentBean>();
			for (CommentModel commentModel : quesitonModel.getComments()) {
				CommentBean bean = new CommentBean();
				bean.setAuthor(commentModel.getAuthor());
				bean.setCommentText(commentModel.getCommentText());
				bean.setDate(new SimpleDateFormat("dd MMM yyyy").format(commentModel.getDate()));

				commnts.add(bean);
			}

		}
		return questionBean;

	}

}
