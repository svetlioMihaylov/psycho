package com.interview.questions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.interview.questions.beans.ResponseBean;
import com.task.tracker.services.TagService;

@Controller
public class TagController {
	
	@Autowired
	TagService tagService;

	@RequestMapping(value = "/createTag", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView sendmail() {
		
		return new ModelAndView("v2/pages/createTag");
	}
	
	@RequestMapping(value = "/createTag/{tagName}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseBean addTag(@PathVariable(value="tagName") String tagName ) {
		
		return tagService.saveTag(tagName);
		
	}
}
