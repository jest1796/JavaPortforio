package katachi.spring.execise.domain.service.imple;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import katachi.spring.execise.domain.model.MUser;

public class LoginUserDetails implements UserDetails {

	private MUser loginUser;
	
	public LoginUserDetails(MUser loginUser) {
		this.loginUser = loginUser;
	}
//	ユーザIDの項目を追加
	public int getUserId() {
		return loginUser.getUserId();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return loginUser.getPassword();
	}

	@Override
	public String getUsername() {
		return loginUser.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

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
