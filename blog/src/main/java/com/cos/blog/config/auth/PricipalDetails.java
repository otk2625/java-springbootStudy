package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.domain.user.User;

import lombok.Data;

@Data
public class PricipalDetails implements UserDetails{
	private User user;
	
	public PricipalDetails(User user) {
		this.user = user;
	}

	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //해당 계정이 만료되었는지?
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //로그인 시도 N번이상 할때 Lock
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 비밀번호 만료
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() { //계정이 활성화 되어 있는지?
		// TODO Auto-generated method stub
		return true;
	}
	
	//권한 체크!
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>(); //사용자 권한을 넣어줘야함
		collectors.add(() ->"ROLE_"+ user.getRole().toString()); //이게 리턴되서 컬렉터에 들어감
		return collectors;
	}

}
