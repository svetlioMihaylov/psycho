package com.interview.questions.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.interview.questions.beans.AddCommentBean;
import com.interview.questions.beans.QuestionBean;
import com.interview.questions.beans.TestBean;
import com.interview.questions.db.entities.TestModel;
import com.interview.questions.populator.EntryImpl;
import com.task.tracker.services.TestService;

@Controller
public class ReviewTestController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping(value = "reviewTest/{testId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView createTestHtml(@PathVariable(value="testId") String testId, HttpSession session)
	{
		TestModel test = testService.findTestById(testId);
		session.setAttribute("reviewTest", test);
		return new  ModelAndView("v2/pages/reviewTest");
	}
	
	@RequestMapping(value = "reviewTestdata", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public @ResponseBody TestBean  createTest( HttpSession session)
	{
		TestModel  test= (TestModel) session.getAttribute("reviewTest");
		TestBean populateBean = testService.populateBean(test);
		return populateBean;
	}
	
	@RequestMapping(value = "reviewQuesiton/{questionId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public @ResponseBody QuestionBean reviewQuesiton(@PathVariable(value="questionId") int questionId, HttpSession session)
	{
		QuestionBean questionBean = testService.getQuestionBean(questionId, session);
		return questionBean;
	}
	
	@RequestMapping(value = "addComment", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public @ResponseBody QuestionBean addComment( @RequestBody AddCommentBean bean, HttpSession session, HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();
		TestModel  testModel = (TestModel) session.getAttribute("reviewTest");
		
		testService.addComment(testModel, bean, principal.getName());
		return null;
	}
	
	@RequestMapping(value = "testsForReview", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<EntryImpl<String, String>> testsForReview(HttpServletRequest request)
	{
//		EntryImpl
		Principal principal = request.getUserPrincipal();
		List<EntryImpl<String, String>> testsForReview = testService.getTestForReview(principal.getName());
		return testsForReview;
	}
	
	

}
