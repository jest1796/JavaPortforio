package katachi.spring.execise.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.execise.domain.model.Subscs;
import katachi.spring.execise.domain.service.SubscService;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;
import katachi.spring.execise.form.SubscForm;

@Controller
public class DeleteController {

	@Autowired
	SubscService subscService;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;


	//	削除画面表示
	@GetMapping("/delete/{id}")
	public String getDelete(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscForm form, @PathVariable("id") Integer id) {
		//ログインユーザ情報収納
		model.addAttribute("user", user);

		//削除確認画面のデータをDBから取得　他のユーザデータにアクセスできないようにuser_idも検索条件に入れる
		Subscs editItem = subscService.findOne(id,user.getUserId());

		//editItemをSubscFormクラスに変換
		form = modelMapper.map(editItem, SubscForm.class);

		//予定情報収納
		model.addAttribute("form", form);
		return "delete";

	}

	//	項目削除実行
	@PostMapping("/delete")
	public String postDelete(@AuthenticationPrincipal LoginUserDetails user,Model model,
			@ModelAttribute SubscForm form) {
	
		subscService.deleteSubsc(form.getId(), user.getUserId());
		return "redirect:index";

	}

}
