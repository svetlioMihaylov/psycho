package my.pack.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.interview.questions.beans.QuestionBean;
import com.interview.questions.beans.ResponseBean;
import com.interview.questions.enums.NotificationType;
import com.interview.questions.misc.NotificationMessages;
import com.task.tracker.services.QuestionService;
import com.task.tracker.services.TagService;


@Controller
@Transactional
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	TagService tagService;
	
	@RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		List<String> tagsList = new ArrayList<>();
		tagsList.add("");
		tagsList.addAll(tagService.getAllTags());
		
		QuestionBean question = new QuestionBean();
		
		ModelAndView modelAndView = new ModelAndView("v2/pages/addQuestion");
		modelAndView.addObject("arrList", tagsList);
		modelAndView.addObject("question", question);

		return modelAndView;
	}
	
	@RequestMapping(value = "/addQuestionPost", method = RequestMethod.POST)
	public @ResponseBody ResponseBean createQuestion(@RequestBody QuestionBean question) {
		questionService.saveQuestion(question);
		return new ResponseBean(NotificationMessages.QUESTION_ADDED_SUCCESSFULLY, NotificationType.INFO, true);
		
	}
	
	@RequestMapping(value = "allQuestions", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	
	public @ResponseBody List<QuestionBean> getAllQuestions()
	{
		List<QuestionBean> quesitonsList = questionService.getQuesitonsList();
		return quesitonsList;
	}

}

