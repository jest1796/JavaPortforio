package katachi.spring.execise.domain.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import katachi.spring.execise.domain.model.Users;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	//	ユーザ登録
	@Override
	public void signup(Users loginUser) {

		//		パスワード暗号化
		String rawPassword = loginUser.getPassword();
		loginUser.setPassword(encoder.encode(rawPassword));

		mapper.signup(loginUser);
	}

	//	ログインユーザ情報取得
	@Override
	public Users getLoginUser(String userName) {

		return mapper.findLoginUser(userName);
	}


}
