package my.pack.login;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class LogInController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String logIn() {
		return "login/login";
	}
}
