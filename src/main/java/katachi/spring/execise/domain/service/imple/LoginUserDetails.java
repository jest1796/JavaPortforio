package katachi.spring.execise.domain.service.imple;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import katachi.spring.execise.domain.model.LoginUser;

public class LoginUserDetails implements UserDetails {

	private LoginUser loginUser;
	
	public LoginUserDetails(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return loginUser.getPass();
	}

	@Override
	public String getUsername() {
		return loginUser.getUsename();
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
