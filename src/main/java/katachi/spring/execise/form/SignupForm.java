package katachi.spring.execise.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupForm {
	
//	ユーザー名
	@NotBlank(groups = ValidGroup1.class)
	private String userName;
	
//	パスワード
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 4,max = 16,groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$",groups = ValidGroup2.class)
	private String password;
	
	
}
