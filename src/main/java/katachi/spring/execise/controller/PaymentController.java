package katachi.spring.execise.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;
import katachi.spring.execise.form.SubscData;

@Controller
@SessionAttributes("dateError") // セッション属性を指定

public class PaymentController {
	
	@Autowired
	private HttpSession session;

	//	月払いの日にち入力画面表示
	@GetMapping("/payMonth")
	public String getPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data) {

		model.addAttribute("user", user);
		SubscData inputData = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData",inputData);
		return "payMonth";

	}
//	日にち処理
	@PostMapping("/payMonth")
	public String postPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			 @Validated @ModelAttribute SubscData data,BindingResult bindingResult) {
		model.addAttribute("user", user);
		
		if(bindingResult.hasErrors()) {
		return getPayMonth(user, model, data);
		}
		
		session.setAttribute("data", data);
		return "redirect:confirm";

	}

	boolean dateError = false; //月日判定のフラグ
	
	//	年払いの月日入力画面表示
	@GetMapping("/payYear")
	public String getPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			SubscData data,boolean dateError) {
		model.addAttribute("user", user);
		
//		存在しない月日だった場合のメッセージ表示用
		model.addAttribute("dateError", dateError);

		SubscData inputData = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData", inputData);
		return "payYear";
	}
	
//	年払いの入力処理処理
	@PostMapping("/payYear")
	public String postPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@Validated @ModelAttribute SubscData data,BindingResult bindingResult,boolean dateError) {
		model.addAttribute("user", user);
		
		int month = data.getMonth();  //入力された月//
		int day = data.getDay();	   //入力された日//
	
//		入力された月日が存在するかどうかの判定
		if (!isValidDate(month, day)) {
			
			dateError = true;	//trueを入れてgetPayYearに戻すことでエラーメッセージ表示させる
            return getPayYear(user, model, data,dateError);  
        }
		
		if(bindingResult.hasErrors()) {
			return getPayYear(user, model, data,dateError);
		}
		
		session.setAttribute("data", data);
		
		return "redirect:confirm";

	}
	
//	入力された月日の正誤判定
	 public static boolean isValidDate(int month, int day) {
	        try {
	            LocalDate.of(2001, month, day); // 2001年はうるう年ではないので、2月29日は無効な日付として扱います
	            return true; // 有効な日付
	        } catch (Exception e) {
	            return false; // 無効な日付
	        }
	    }
}
