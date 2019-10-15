package com.interview.questions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.interview.questions.beans.ResponseBean;
import com.interview.questions.beans.UserBean;
import com.task.tracker.services.UserServiceCustom;

@Controller
public class UserController {
	
	@Autowired
	UserServiceCustom userService;
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView loadUserForm() {
		
		return new ModelAndView("v2/pages/createUser");
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseBean createUser(@RequestBody UserBean userBean) {
		
		
		return userService.createUser(userBean);
	}
}
