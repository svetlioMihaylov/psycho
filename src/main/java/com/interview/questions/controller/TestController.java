package com.interview.questions.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.interview.questions.beans.QuestionBean;
import com.interview.questions.beans.TestBean;
import com.interview.questions.beans.UpdateQuestionBean;
import com.interview.questions.db.dao.QuestionDao;
import com.interview.questions.db.entities.QuestionModel;
import com.task.tracker.services.TagService;
import com.task.tracker.services.TestService;
import com.task.tracker.services.UserServiceCustom;

@Controller
public class TestController {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	UserServiceCustom userServiceCustom;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	TestService testService;
	
	@RequestMapping(value = "createTest", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView  createTest()
	{
		ModelAndView modelAndView = new ModelAndView("v2/pages/createTest");
		
		List<String> tags = new ArrayList<String>();
		tags.add("");
		tags.addAll(tagService.getAllTags());
		modelAndView.addObject("tags", tags);

		List<String> usersList = new ArrayList<String>(); 
		usersList.add("");
		usersList.addAll(userServiceCustom.getAllUsersNames());	
		modelAndView.addObject("usersList", usersList);
		
		
		QuestionModel question = new QuestionModel();
		modelAndView.addObject("question", question);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "createTest", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public  @ResponseBody List<QuestionBean>  generateTest(@RequestBody TestBean testBean, HttpSession session)
	{
		
		List<QuestionBean> data = testService.getBeans(testBean, session);
		
		return data;
	}
	
	@RequestMapping(value = "updateTestQuestion", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public  @ResponseBody List<QuestionBean>  updateTestQuestion(@RequestBody UpdateQuestionBean updateQuestionBean, HttpSession session)
	{
		testService.updateQuestion(updateQuestionBean, session);
		return null;
	}
	
	
		
}
