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

import katachi.spring.execise.domain.model.MUser;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.form.GroupOrder;
import katachi.spring.execise.form.SignupForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignupController {

	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/signup")
	public String getSignup( @ModelAttribute SignupForm form) {
		return "signup";
	}
	
	@PostMapping("/signup") 
	public String postSignup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
			BindingResult bindingResult,Model model){
		
		if(bindingResult.hasErrors()) {
			return getSignup(form);
		}
		
		log.info(form.toString());
	
		MUser loginUser = modelMapper.map(form, MUser.class);
		
		try {
		userService.signup(loginUser);
		}catch(DataAccessException e) {
			 model.addAttribute("signupError", "ユーザー登録に失敗しました");
	            return "signup";
		}
		return "redirect:/index";
		
	}
}
