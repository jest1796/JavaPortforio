package katachi.spring.execise.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import katachi.spring.execise.domain.model.Item;
import katachi.spring.execise.domain.service.ItemService;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;
import katachi.spring.execise.form.SubscData;

@Controller
public class EditController {

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;

	//	サービスと月・年払いの選択、料金の編集画面表示
	@GetMapping("/edit/{id}")
	public String getEdit(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data, @PathVariable("id") Integer id) {
		//ログインユーザ情報収納
		model.addAttribute("user", user);

		//修正画面のデータをDBから取得
		Item editItem = itemService.findOne(id);
		System.out.println(editItem);
		//ログインユーザ情報収納
		model.addAttribute("user", user);

		//editItemをSubscDataクラスに変換
		data = modelMapper.map(editItem, SubscData.class);

		//予定情報収納
		model.addAttribute("data", data);
		
		return "edit";

	}
	
	@Autowired
    private HttpSession session;

	//	サービスと月・年払いの選択、料金の編集実行
	@PostMapping("/edit")
	public String postEdit(@AuthenticationPrincipal LoginUserDetails user,Model model,
			 @Validated SubscData data,BindingResult bindingResult, Integer id) {
		
		if(bindingResult.hasErrors()) {
		return getEdit(user, model, data, id);
		}
		
		session.setAttribute("data", data);
		
//		年払いの場合
		if(data.getPay()==2) {
			return "redirect:editPayYear";
		}
		
//		月払いの場合
		return "redirect:editPayMonth";
		
	}

	
//	月払いの日にち入力画面表示
	@GetMapping("/editPayMonth")
	public String getPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data) {

		model.addAttribute("user", user);
		SubscData inputData = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData",inputData);
		return "editPayMonth";
	}
	
//	年払いの月日入力画面表示
	@GetMapping("/editPayYear")
	public String getPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			SubscData data) {
		model.addAttribute("user", user);

		SubscData inputData = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData", inputData);
		return "editPayYear";
	}
}
