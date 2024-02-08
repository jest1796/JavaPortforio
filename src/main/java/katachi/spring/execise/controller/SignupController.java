package katachi.spring.execise.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import katachi.spring.execise.domain.model.MUser;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.form.SignupForm;

@Controller
public class SignupController {

	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/signup")
	public String getSignup() {
			
		return "signup";
		
	}
	
	@PostMapping("/signup") 
	public String postSignup(Model model, @ModelAttribute SignupForm form){
		
		MUser loginUser = modelMapper.map(form, MUser.class);
		
		userService.signup(loginUser);
		return "redirect:/index";
		
	}
}
