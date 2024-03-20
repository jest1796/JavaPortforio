package katachi.spring.execise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import katachi.spring.execise.domain.model.Item;
import katachi.spring.execise.domain.service.ItemService;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;

@Controller
public class IndexController {


	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;
	
	@GetMapping("/index")
	public String getLogin(@AuthenticationPrincipal LoginUserDetails user,Model model) {
		
//		ログインユーザのデータ登録
		model.addAttribute("user", user);
		
//		利用サービス一覧情報取得
		List<Item> item = itemService.getItem(user.getUserId());	
		
//		一覧表データ登録
		model.addAttribute("item", item);
		
		return "index";
		
	}
}
