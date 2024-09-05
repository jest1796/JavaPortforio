package katachi.spring.execise.domain.service.imple;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import katachi.spring.execise.domain.model.Users;


/**
 * SpringSecurityのUserDetailsを継承してログインユーザの情報処理
 */
public class LoginUserDetails implements UserDetails {

	private Users loginUser;
	
	public LoginUserDetails(Users loginUser) {
		this.loginUser = loginUser;
	}
	
	//ユーザIDの項目を追加
	public int getUserId() {
		return loginUser.getUserId();
	}
	
	//ユーザ権限（使用しないのでnullを返却）
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	//ログインパスワードを追加
	@Override
	public String getPassword() {
		return loginUser.getPassword();
	}

	//ログインユーザの名前を追加
	@Override
	public String getUsername() {
		return loginUser.getUserName();
	}

	//アカウントの有効期限を判別
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//アカウントがロックされていないことを判別
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
