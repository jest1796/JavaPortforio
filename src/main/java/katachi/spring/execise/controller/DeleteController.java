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

import katachi.spring.execise.domain.model.Item;
import katachi.spring.execise.domain.service.ItemService;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;
import katachi.spring.execise.form.SubscData;

@Controller
public class DeleteController {

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;


	//	削除画面表示
	@GetMapping("/delete/{id}")
	public String getDelete(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data, @PathVariable("id") Integer id) {
		//ログインユーザ情報収納
		model.addAttribute("user", user);

		//削除確認画面のデータをDBから取得
		Item editItem = itemService.findOne(id);

		//editItemをSubscDataクラスに変換
		data = modelMapper.map(editItem, SubscData.class);

		//予定情報収納
		model.addAttribute("data", data);
		return "delete";

	}

	//	項目削除実行
	@PostMapping("/delete")
	public String postDelete(Model model,
			@ModelAttribute SubscData data) {
		System.out.println(data);
		itemService.deleteItem(data.getId());
		return "redirect:index";

	}

}
