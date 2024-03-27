package katachi.spring.execise.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import katachi.spring.execise.domain.model.Subscs;
import katachi.spring.execise.domain.service.SubscService;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;
import katachi.spring.execise.form.SubscForm;

@Controller
public class ConfirmController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	SubscService subscService;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;
	
	
//	登録内容確認画面表示
	@GetMapping("/confirm")
	public String getConfirm(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscForm form) {
		
		model.addAttribute("user", user);
		
		SubscForm inputData = (SubscForm) session.getAttribute("form");
		model.addAttribute("inputData",inputData);
		
		return "confirm";
		
	}
	
//	登録内容DBに書き込み実行
	@PostMapping("/confirm")
	public String postConfirm(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscForm form,Subscs subsc) {
		model.addAttribute("user", user);
		
//		formをSubsc型に変換
		subsc = modelMapper.map(form, Subscs.class);
		
//		DBに項目変更を実行
		subscService.registSubsc(subsc);
			
		return "redirect:index";
			
		
	}
	
//	編集内容の確認画面表示
	@GetMapping("/editConfirm")
	public String getEditconfirm(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscForm form) {
		
		model.addAttribute("user", user);
		SubscForm inputData = (SubscForm) session.getAttribute("form");
		model.addAttribute("inputData",inputData);
		
		return "editConfirm";
		
	}
	
//	編集後の内容をDBに書き込み実行
	@PostMapping("/editConfirm")
	public String postEditconfirm(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscForm form,Subscs subsc) {
		model.addAttribute("user", user);
		
//		SubscFormクラスをSubsc型に変換
		subsc = modelMapper.map(form, Subscs.class);
		
//		DBに項目変更を実行
		subscService.updateSubsc(subsc);
			
		return "redirect:index";
			
		
	}
}
