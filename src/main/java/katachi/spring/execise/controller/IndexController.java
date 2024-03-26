package katachi.spring.execise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import katachi.spring.execise.domain.model.Subsc;
import katachi.spring.execise.domain.service.SubscService;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;

@Controller
public class IndexController {


	@Autowired
	SubscService subscService;

	@Autowired
	UserService userService;
	
	@Autowired
    private HttpSession session;
	
	@GetMapping("/index")
	public String getLogin(@AuthenticationPrincipal LoginUserDetails user,Model model) {
		
//		ログインユーザのデータ登録
		model.addAttribute("user", user);
		
//		利用サービス一覧情報取得
		List<Subsc> subscs = subscService.getSubscs(user.getUserId());
		
//		一覧表データ登録
		model.addAttribute("subscs", subscs);
		
//		セッションの情報を削除しておく
		session.removeAttribute("form");
		
		
		return "index";
		
	}
}
