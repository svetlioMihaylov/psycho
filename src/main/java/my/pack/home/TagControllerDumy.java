package my.pack.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.questions.db.entities.TagModel;
import com.task.tracker.services.TagService;


@Controller
@Transactional
public class TagControllerDumy {
	
	@Autowired
	TagService tagService;
	
	@RequestMapping(value = "/addTags", method = RequestMethod.GET)
	public ModelAndView addTags() {
		List <TagModel> list = new ArrayList<TagModel>();
		list.add(addTag("Application Developer"));
		list.add(addTag("Application Support Analyst"));
		list.add(addTag("Applications Engineer"));
		list.add(addTag("Associate Developer"));
		list.add(addTag("Chief Technology Officer (CTO)"));
		list.add(addTag("Chief Information Officer (CIO) "));
		list.add(addTag("Computer and Information Systems Manager"));
		list.add(addTag("Computer Systems Manager"));
		list.add(addTag("Customer Support Administrator"));
		list.add(addTag("Customer Support Specialist"));
		list.add(addTag("Data Center Support Specialist"));
		list.add(addTag("Data Quality Manager"));
		list.add(addTag("Database Administrator"));
		list.add(addTag("Desktop Support Manager"));
		list.add(addTag("Desktop Support Specialist"));
		list.add(addTag("Developer"));
		list.add(addTag("Director of Technology"));
		list.add(addTag("Front End Developer"));
		list.add(addTag("Help Desk Specialist"));
		list.add(addTag("Help Desk Technician"));
		list.add(addTag("Information Technology Coordinator"));
		list.add(addTag("Information Technology Director"));
		list.add(addTag("Information Technology Manager"));
		list.add(addTag("IT Support Manager"));
		list.add(addTag("IT Support Specialist"));
		
		for(TagModel tag : list)
		{
			tagService.saveTag(tag);
		}
		
		return null;
	}
	
	private TagModel addTag(String name)
	{
		TagModel tag = new TagModel();
		tag.setName(name);
		return tag;
	}

}
