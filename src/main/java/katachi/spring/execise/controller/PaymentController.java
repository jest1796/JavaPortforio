package katachi.spring.execise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;
import katachi.spring.execise.form.SubscData;

@Controller
public class PaymentController {

	@Autowired
	private HttpSession session;

	//	月払いの日にち入力画面表示
	@GetMapping("/payMonth")
	public String getPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data) {

		model.addAttribute("user", user);
		data = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData", data);
		return "payMonth";

	}
//	日にち処理処理
	@PostMapping("/payMonth")
	public String postPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			 @Validated @ModelAttribute SubscData data,BindingResult bindingResult) {
		model.addAttribute("user", user);
		
		if(bindingResult.hasErrors()) {
		return getPayMonth(user, model, data);
		}
		
		return "check";

	}

	//	年払いの月日入力画面表示
	@GetMapping("/payYear")
	public String getPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			SubscData data) {
		model.addAttribute("user", user);

		SubscData inputData = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData", inputData);
		return "payYear";
	}
	
//	年払いの入力処理処理
	@PostMapping("/payYear")
	public String postPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@Validated @ModelAttribute SubscData data,BindingResult bindingResult) {
		model.addAttribute("user", user);
		
		if(bindingResult.hasErrors()) {
			return getPayYear(user, model, data);
		}
				
		System.out.println(data);
		System.out.println(data.getMonth());
		System.out.println(data.getDay());
		return "check";

	}
}
