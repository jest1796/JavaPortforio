package katachi.spring.execise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

	@GetMapping("/index")
	public String getLogin() {
		return "index";
		
	}
}
