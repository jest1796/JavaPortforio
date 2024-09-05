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
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;
import katachi.spring.execise.form.SubscForm;

/**
 * 支払い内容登録画面コントローラ
 */
@Controller
@SessionAttributes("dateError") // セッション属性を指定

public class PaymentController {
	
	@Autowired
	private HttpSession session;

	/**
	 * 月払いの日にち入力画面表示
	 * @param user
	 * @param model
	 * @param form
	 * @return 画面名
	 */
	@GetMapping("/payMonth")
	public String getPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscForm form) {

		model.addAttribute("user", user);
		SubscForm inputData = (SubscForm) session.getAttribute("form");
		model.addAttribute("inputData",inputData);
		return "payMonth";

	}
	
	/**
	 * 日にち処理
	 * @param user
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @return 画面名
	 */
	@PostMapping("/payMonth")
	public String postPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			 @Validated @ModelAttribute SubscForm form,BindingResult bindingResult) {
		model.addAttribute("user", user);
		
		//入力エラー有の場合
		if(bindingResult.hasErrors()) {
		return getPayMonth(user, model, form);
		}
		
		session.setAttribute("form", form);
		return "redirect:confirm";
	}

	//月日判定のフラグ
	boolean dateError = false; 
	
	//月日の正誤判定クラス
	@Autowired
	private isValidDate dateValidator; 
		
	/**
	 * 年払いの月日入力画面表示
	 * @param user
	 * @param model
	 * @param form
	 * @param dateError
	 * @return 画面名
	 */
	@GetMapping("/payYear")
	public String getPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			SubscForm form,boolean dateError) {
		model.addAttribute("user", user);
		
		//存在しない月日だった場合のメッセージ表示用
		model.addAttribute("dateError", dateError);

		SubscForm inputData = (SubscForm) session.getAttribute("form");
		model.addAttribute("inputData", inputData);
		return "payYear";
	}
	
	//年払いの入力処理処理
	@PostMapping("/payYear")
	public String postPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@Validated @ModelAttribute SubscForm form,BindingResult bindingResult,boolean dateError) {
		model.addAttribute("user", user);
		
		int month = form.getMonth();  //入力された月//
		int day = form.getDay();	   //入力された日//
	
		//入力された月日が存在するかどうかの判定
		if (!dateValidator.isValidDate(month, day)) {
			
			//trueを入れてgetPayYearに戻すことでエラーメッセージ表示させる
			dateError = true;	
            return getPayYear(user, model, form,dateError);  
        }
		
		//入力エラー有の場合
		if(bindingResult.hasErrors()) {
			return getPayYear(user, model, form,dateError);
		}
		
		session.setAttribute("form", form);
		
		return "redirect:confirm";

	}
	
	 
}
