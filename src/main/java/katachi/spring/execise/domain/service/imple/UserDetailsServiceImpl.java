package katachi.spring.execise.domain.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import katachi.spring.execise.domain.model.Users;
import katachi.spring.execise.domain.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		// ユーザ情報取得
		Users loginUser = service.getLoginUser(userName);

		//ユーザが存在しない場合
		if (loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		UserDetails userDetails = new LoginUserDetails(loginUser);
		return userDetails;
	}

}
