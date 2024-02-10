package katachi.spring.execise.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupForm {
	@NotBlank
	private String userName;
	
	@NotBlank
	@Length(min = 4,max = 100)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;
	
	
}
