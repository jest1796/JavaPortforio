package katachi.spring.execise.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.execise.domain.model.MUser;
import katachi.spring.execise.domain.service.UserService;
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
	public String postSignup(@ModelAttribute @Validated SignupForm form,
			BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			return getSignup(form);
		}
		
		log.info(form.toString());
	
		MUser loginUser = modelMapper.map(form, MUser.class);
		
		userService.signup(loginUser);
		return "redirect:/login";
		
	}
}
