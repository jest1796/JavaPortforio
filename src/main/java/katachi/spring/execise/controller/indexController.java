package katachi.spring.execise.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import katachi.spring.execise.domain.service.imple.LoginUserDetails;

@Controller
public class indexController {

	@GetMapping("/index")
	public String getLogin(@AuthenticationPrincipal LoginUserDetails user,Model model) {
		
//		ログインユーザのデータ登録
		model.addAttribute("user", user);
		System.out.println(user.getUsername());
		return "index";
		
	}
}
