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
import katachi.spring.execise.domain.model.Item;
import katachi.spring.execise.domain.service.ItemService;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;
import katachi.spring.execise.form.SubscData;

@Controller
public class ConfirmController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;
	
//	登録内容確認画面表示
	@GetMapping("/confirm")
	public String getConfirm(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data) {
		
		model.addAttribute("user", user);
		SubscData inputData = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData",inputData);
		
		return "confirm";
		
	}
	
//	登録内容DBに書き込み実行
	@PostMapping("/confirm")
	public String postConfirm(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data,Item item) {
		model.addAttribute("user", user);
		
//		SubscDataクラスをItem型に変換
		item = modelMapper.map(data, Item.class);
		
//		DBに項目変更を実行
		itemService.registItem(item);
			
		return "redirect:index";
			
		
	}
	
//	編集内容の確認画面表示
	@GetMapping("/editConfirm")
	public String getEditconfirm(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data) {
		
		model.addAttribute("user", user);
		SubscData inputData = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData",inputData);
		
		return "editConfirm";
		
	}
	
//	編集後の内容をDBに書き込み実行
	@PostMapping("/editConfirm")
	public String postEditconfirm(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data,Item item) {
		model.addAttribute("user", user);
		System.out.println("POST "+data);
//		SubscDataクラスをItem型に変換
		item = modelMapper.map(data, Item.class);
		
//		DBに項目変更を実行
		itemService.updateItem(item);
			
		return "redirect:index";
			
		
	}
}
