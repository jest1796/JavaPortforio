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
import katachi.spring.execise.form.SubscForm;

@Controller
public class AddServiceController {
	
//	サービス追加画面表示
	@GetMapping("/addService")
	public String getAddService(@AuthenticationPrincipal LoginUserDetails user,Model model,
			@ModelAttribute SubscForm form) {
//		ログインユーザのデータ登録
		model.addAttribute("user", user);
		form.setPay(1);
	 
	    return "addService";
		
	}
	
	@Autowired
    private HttpSession session;
	
//	サービス名と月・年払いの選択、料金の入力
	@PostMapping("/addService")
	public String postAddService(@AuthenticationPrincipal LoginUserDetails user,Model model,
			 @Validated SubscForm form,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
		return getAddService(user, model, form);
		}

//		入力内容を保持する
		session.setAttribute("form", form);
		
//		年払いの場合
		if(form.getPay()==2) {
			return "redirect:payYear";
		}
		
//		月払いの場合
		return "redirect:payMonth";
		
	}
	
}
