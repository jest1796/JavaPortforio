package katachi.spring.execise.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.execise.domain.model.Users;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.form.SignupForm;
import lombok.extern.slf4j.Slf4j;

/**
 * ユーザ登録コントローラ
 */
@Controller
@Slf4j
public class SignupController {

	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * ユーザ登録画面表示
	 * @param form
	 * @return 画面名
	 */
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form) {
		return "signup";
	}
	
	/**
	 * ユーザ登録実行
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return 画面名
	 */
	@PostMapping("/signup") 
	public String postSignup(@ModelAttribute @Validated SignupForm form,
			BindingResult bindingResult,Model model){
		
		//入力エラー有の場合
		if(bindingResult.hasErrors()) {
			return getSignup(form);
		}
		
		log.info(form.toString());
	
		Users loginUser = modelMapper.map(form, Users.class);
		
		try {
		userService.signup(loginUser);
		} catch (DataAccessException e) {
			 model.addAttribute("signupError", "ユーザー登録に失敗しました");
	            return "signup";
		}
		return "redirect:/index";
		
	}
}
