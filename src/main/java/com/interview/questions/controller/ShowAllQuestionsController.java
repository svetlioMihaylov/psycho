package com.interview.questions.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.interview.questions.db.entities.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
public class ShowAllQuestionsController {
    @Autowired
    TestService testService;

    @RequestMapping(value = "showAllQuestions", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Scope("session")
    public @ResponseBody ModelAndView showAllQuestions(HttpSession session)
    {
        ModelAndView modelAndView = new ModelAndView("v2/pages/showAllQuestions");
        QuestionModel question = new QuestionModel();
        modelAndView.addObject("question", question);
        System.out.println("show all questions!");

        return modelAndView;
    }

    @RequestMapping(value = "showQuesiton/{questionId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Scope("session")
    public @ResponseBody QuestionBean showAllQuestions(@PathVariable(value="questionId") int questionId, HttpSession session)
    {
        QuestionBean questionBean = testService.getQuestionBean(questionId, session);
        return questionBean;
    }

    @RequestMapping(value = "showTestdata", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Scope("session")
    public @ResponseBody TestBean  showTest( HttpSession session)
    {
        TestModel test= (TestModel) session.getAttribute("reviewTest");
        TestBean populateBean = testService.populateBean(test);
        return populateBean;
    }

}