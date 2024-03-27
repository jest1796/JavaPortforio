package katachi.spring.execise.domain.model;


//ログインユーザデータ用
import java.util.Date;

import lombok.Data;
@Data
public class Users {
	private int userId;
	private String userName;
	private String password;
	private Date create_date_time;

}
