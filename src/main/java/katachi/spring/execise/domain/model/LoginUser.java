package katachi.spring.execise.domain.model;


//ログインユーザデータ用
import java.util.Date;

import lombok.Data;
@Data
public class LoginUser {
	private int id;
	private String usename;
	private String pass;
	private Date create_date_time;
	private Date update_date_time;

}
