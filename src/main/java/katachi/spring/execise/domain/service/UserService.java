package katachi.spring.execise.domain.service;

import katachi.spring.execise.domain.model.Users;

public interface UserService {
//	ユーザ登録
	public void signup(Users loginUser);

//	ログインユーザ取得
	public Users getLoginUser(String userName);

	
}
