package katachi.spring.execise.domain.service;

import katachi.spring.execise.domain.model.MUser;

public interface UserService {
//	ユーザ登録
	public void signup(MUser loginUser);

//	ログインユーザ取得
	public MUser getLoginUser(String userName);
}
