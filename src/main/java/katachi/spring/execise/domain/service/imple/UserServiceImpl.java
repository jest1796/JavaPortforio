package katachi.spring.execise.domain.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.spring.execise.domain.model.MUser;
import katachi.spring.execise.domain.service.UserService;
import katachi.spring.execise.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	
//	ユーザ登録
	@Override
	public void signup(MUser loginUser) {
		mapper.signup(loginUser);
	}

//	ログインユーザ情報取得
	@Override
	public MUser getLoginUser(String userName) {
		
		return mapper.findLoginUser(userName);
	}

}
