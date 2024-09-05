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
import katachi.spring.execise.domain.model.Subscs;
import katachi.spring.execise.domain.service.SubscService;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.domain.service.imple.LoginUserDetails;
import katachi.spring.execise.form.SubscForm;

/**
 * 編集画面コントローラ
 */
@Controller
public class EditController {

	@Autowired
	SubscService itemService;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
    private HttpSession session;
	
	//月日の正誤判定
	@Autowired
	private isValidDate dateValidator;  

	/**
	 * サービスと月・年払いの選択、料金の編集画面表示
	 * @param user
	 * @param model
	 * @param form
	 * @param id
	 * @return 画面名
	 */
	@GetMapping("/edit/{id}")
	public String getEdit(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscForm form, @PathVariable("id") Integer id) {
		//ログインユーザ情報収納
		model.addAttribute("user", user);

		//修正画面のデータをDBから取得　他のユーザデータにアクセスできないようにuser_idも検索条件に入れる
		Subscs editItem = itemService.findOne(id,user.getUserId());
		
		//ログインユーザ情報収納
		model.addAttribute("user", user);

		//editItemをSubscFormクラスに変換
		form = modelMapper.map(editItem, SubscForm.class);

		//予定情報収納
		model.addAttribute("form", form);
		
		return "edit";

	}
	
	/**
	 * サービスと月・年払いの選択、料金の編集実行
	 * @param user
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @param id
	 * @return 画面名
	 */
	@PostMapping("/edit")
	public String postEdit(@AuthenticationPrincipal LoginUserDetails user,Model model,
			 @Validated SubscForm form,BindingResult bindingResult, Integer id) {
		
		if(bindingResult.hasErrors()) {
		return getEdit(user, model, form, id);
		}
		
		session.setAttribute("form", form);
		
		//年払いの場合
		if(form.getPay()==2) {
			return "redirect:editPayYear";
		}
		
		//月払いの場合
		return "redirect:editPayMonth";
		
	}

	/**
	 * 月払いの日にち編集入力画面表示
	 * @param user
	 * @param model
	 * @param form
	 * @return 画面名
	 */
	@GetMapping("/editPayMonth")
	public String getPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscForm form) {
		
		model.addAttribute("user", user);
		
		SubscForm inputData = (SubscForm) session.getAttribute("form");
		model.addAttribute("inputData",inputData);
		
		return "editPayMonth";
	}
	
	/**
	 * 月払いの編集日にち処理
	 * @param user
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @param dateError
	 * @return 画面名
	 */
	@PostMapping("/editPayMonth")
	public String postPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			 @Validated @ModelAttribute SubscForm form,BindingResult bindingResult,boolean dateError) {
		model.addAttribute("user", user);
		
		if(bindingResult.hasErrors()) {
		return getPayMonth(user, model, form);
		}
		
		session.setAttribute("form", form);
		
		return "redirect:editConfirm";
	}
	
	//月日判定のフラグ
	boolean dateError = false; 
	
	/**
	 * 年払いの編集月日入力画面表示
	 * @param user
	 * @param model
	 * @param form
	 * @param dateError
	 * @return 画面名
	 */
	@GetMapping("/editPayYear")
	public String getEditPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			SubscForm form,boolean dateError) {
		model.addAttribute("user", user);
		
		//存在しない月日だった場合のメッセージ表示用
		model.addAttribute("dateError", dateError);

		SubscForm inputData = (SubscForm) session.getAttribute("form");
		model.addAttribute("inputData", inputData);
		return "editPayYear";
	}
	
	/**
	 * 年払いの編集入力処理処理
	 * @param user
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @param dateError
	 * @return 画面名
	 */
	@PostMapping("/editPayYear")
	public String postPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@Validated @ModelAttribute SubscForm form,BindingResult bindingResult,boolean dateError) {
		model.addAttribute("user", user);
	
		int month = form.getMonth();  //入力された月//
		int day = form.getDay();	   //入力された日//
		
		//無効な月日だった場合
		if (!dateValidator.isValidDate(month, day)) {
			dateError = true;	//trueを入れてgetEditPayYearに戻すことでエラーメッセージ表示させる
            return getEditPayYear(user, model, form,dateError);  
        }
		
		if(bindingResult.hasErrors()) {
			return getEditPayYear(user, model, form,dateError);
		}
		
		session.setAttribute("form", form);
		
		return "redirect:editConfirm";

	}
	
}
