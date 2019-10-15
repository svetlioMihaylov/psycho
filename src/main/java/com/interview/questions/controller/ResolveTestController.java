package com.interview.questions.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.interview.questions.beans.QuestionAnswerBean;
import com.interview.questions.beans.ResponseBean;
import com.interview.questions.beans.TestQuestionBean;
import com.interview.questions.beans.TestValidationBean;
import com.interview.questions.db.entities.TestModel;
import com.interview.questions.db.entities.TestQuestionModel;
import com.interview.questions.enums.NotificationType;
import com.interview.questions.enums.QuestionType;
import com.interview.questions.misc.NotificationMessages;
import com.task.tracker.services.TestService;


@Controller
public class ResolveTestController {
	
	@Autowired
	TestService testService;
	
	
	@RequestMapping(value = "dotest/{testId}/{number}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public ModelAndView  createTest(@PathVariable(value="testId") String testId,  @RequestParam(required = false, value="number") String number, HttpSession session)
	{
		
		TestModel testModel = (TestModel) session.getAttribute("testInProgress");
		if(null == testModel)
		{
		ModelAndView modelAndView = new ModelAndView("v2/pages/validateMail");
		return modelAndView;
		}
			
			return null;
		
	}
	
	@RequestMapping(value = "validatemail"	, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public @ResponseBody TestQuestionBean validateMail(@RequestBody TestValidationBean bean ,HttpSession session)
	{
		System.out.println(bean);
		TestModel testModel = testService.findTestForCandidate(bean);
		if(null != testModel)
		{
			session.setAttribute("test", testModel);
		}
		TestQuestionModel question = getQuestion(testModel, 1);
		
		TestQuestionBean testBean = createOutputBean(question);
		
		testBean.setNumber(1);
		testBean.setQuestionCount(testModel.getQuestions().size());
		return testBean;
		
	}

	private TestQuestionBean createOutputBean(TestQuestionModel question) {
		TestQuestionBean testBean= new TestQuestionBean();
		testBean.setQustionText(question.getQuestionText());
		testBean.setQuestionAnswer(question.getSelectedAnswer());
		return testBean;
	}
	
	@RequestMapping(value = "/answer" , method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public @ResponseBody ResponseBean test(@RequestBody QuestionAnswerBean bean ,HttpSession session)
	{
		TestModel test = (TestModel) session.getAttribute("test");
		
		for(TestQuestionModel testQuestion : test.getQuestions())
		{
			if(testQuestion.getNumber() == bean.getNumber())
			{
////				if(testQuestion.getQuestionType().equals(QuestionType.PLAIN_TEXT))
////				{
////					testQuestion.setQuestionAnswer(bean.getAnswer());
////				}
////				else
////				{
////					for(String answer : bean.getMultipleChoise())
////					{
////						testQuestion.getMultipleChoise().put(answer, true);
////					}
////				}
//				break;
			}
		}
		testService.save(test);
		
		return new ResponseBean(NotificationMessages.ANSWER_SAVED_SUCCESSFULLY, NotificationType.INFO, false);
		
	}
	
	@RequestMapping(value = "/getquestion/{questionId}" , method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public  @ResponseBody TestQuestionBean  getQuestion(@PathVariable(value="questionId") int questionId, HttpSession session)
	{
		TestModel testModel = (TestModel) session.getAttribute("test");
		for(TestQuestionModel testQuestion : testModel.getQuestions())
		{
			if (testQuestion.getNumber() == questionId)
			{
				TestQuestionBean bean = createOutputBean(testQuestion);
				return bean;
			}
		}
		return null;
	}
	
	
	private TestQuestionModel getQuestion(TestModel test, int number)
	{
		for(TestQuestionModel a :  test.getQuestions())
		{
			if(a.getNumber() == number)
				return a;
		}
		return null;
	}
	
	
	@RequestMapping(value = "test"
			+ "/{testId}"
			, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public ModelAndView  test()
	{
		ModelAndView modelAndView = new ModelAndView("v2/pages/validateMail");
//		QuestionBean question = new QuestionBean();
//		modelAndView.addObject("question", question);
		return modelAndView;
	
	}
	
	@RequestMapping(value = "/test/complete"	, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@Scope("session")
	public void completeTest(HttpSession session)
	{
		TestModel testModel = (TestModel) session.getAttribute("test");
		testModel.setCompleted(true);
		testService.save(testModel);
//		QuestionBean question = new QuestionBean();
//		modelAndView.addObject("question", question);
	
	}
}
