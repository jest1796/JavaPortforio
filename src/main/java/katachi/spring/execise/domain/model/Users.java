package katachi.spring.execise.domain.model;

import java.util.Date;

import lombok.Data;
/**
 * ログインユーザデータのModelクラス
 */
@Data
public class Users {
	private int userId;
	private String userName;
	private String password;
	private Date create_date_time;

}
