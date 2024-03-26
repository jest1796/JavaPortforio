package katachi.spring.execise.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllAdvice {

	
	//例外処理//
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {

		
		return "error";
	}

}
