package katachi.spring.execise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインコントローラ
 */
@Controller

public class LoginController {

	@GetMapping("/login")
	public String getLogin() {
		
		return "login";
		
	}
}
