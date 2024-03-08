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
public class AddServiceController {
	
//	サービス追加画面表示
	@GetMapping("/addService")
	public String getAddService(@AuthenticationPrincipal LoginUserDetails user,Model model,
			@ModelAttribute SubscData data) {
//		ログインユーザのデータ登録
		model.addAttribute("user", user);
		data.setPay(1);
	 
	    return "addService";
		
	}
	
	@Autowired
    private HttpSession session;
	
//	サービス名と月・年払いの選択、料金の入力
	@PostMapping("/addService")
	public String postAddService(@AuthenticationPrincipal LoginUserDetails user,Model model,
			 @Validated SubscData data,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			System.out.println(data);
		return getAddService(user, model, data);
		}

		session.setAttribute("data", data);
		
//		年払いの場合
		if(data.getPay()==2) {
			return "redirect:payYear";
		}
		
//		月払いの場合
		return "redirect:payMonth";
		
	}
	
}
