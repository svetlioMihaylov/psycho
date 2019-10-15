package my.pack.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.task.tracker.services.UserServiceCustom;

@Controller
@Transactional
public class DashBoardController {

	@Autowired
	UserServiceCustom userService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView modelAndView = new ModelAndView("v2/pages/index2");
		return modelAndView;
	}
}
