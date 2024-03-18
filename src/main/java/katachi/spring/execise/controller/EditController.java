package katachi.spring.execise.controller;

import java.time.LocalDate;

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

	
//	月払いの日にち編集入力画面表示
	@GetMapping("/editPayMonth")
	public String getPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@ModelAttribute SubscData data) {

		model.addAttribute("user", user);
		SubscData inputData = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData",inputData);
		System.out.println("日にち入力"+inputData);
		return "editPayMonth";
	}
	
	//月払いの	編集日にち処理
	@PostMapping("/editPayMonth")
	public String postPayMonth(@AuthenticationPrincipal LoginUserDetails user, Model model,
			 @Validated @ModelAttribute SubscData data,BindingResult bindingResult,boolean dateError) {
		model.addAttribute("user", user);
		
		if(bindingResult.hasErrors()) {
		return getPayMonth(user, model, data);
		}
		
		session.setAttribute("data", data);
		
		return "redirect:editConfirm";
	}
	
	boolean dateError = false; //月日判定のフラグ
	
//	年払いの編集月日入力画面表示
	@GetMapping("/editPayYear")
	public String getEditPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			SubscData data,boolean dateError) {
		model.addAttribute("user", user);
		
//		存在しない月日だった場合のメッセージ表示用
		model.addAttribute("dateError", dateError);

		SubscData inputData = (SubscData) session.getAttribute("data");
		model.addAttribute("inputData", inputData);
		return "editPayYear";
	}
	
//	年払いの編集入力処理処理
	@PostMapping("/editPayYear")
	public String postPayYear(@AuthenticationPrincipal LoginUserDetails user, Model model,
			@Validated @ModelAttribute SubscData data,BindingResult bindingResult,boolean dateError) {
		model.addAttribute("user", user);
	
		int month = data.getMonth();  //入力された月//
		int day = data.getDay();	   //入力された日//
		
		if (!isValidDate(month, day)) {
			System.out.println("無効な日付です");
			dateError = true;	//trueを入れてgetEditPayYearに戻すことでエラーメッセージ表示させる
            return getEditPayYear(user, model, data,dateError);  
        }
		
		if(bindingResult.hasErrors()) {
			return getEditPayYear(user, model, data,dateError);
		}
		
		session.setAttribute("data", data);
		System.out.println(data);
		return "redirect:editConfirm";

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
