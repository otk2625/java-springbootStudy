package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.blog.domain.user.User;

import lombok.Data;

@Data
public class PricipalDetails implements UserDetails, OAuth2User{
	private User user;
	private Map<String, Object> attributes; // OAuth제공자로 부터 받은 회원 정보
	private boolean oauth = false;
	
	public PricipalDetails(User user) {
		this.user = user;
	}
	
	public PricipalDetails(User user, Map<String, Object> attributes) {
		this.attributes = attributes;
		this.user = user;
		this.oauth = true;
	}
	
	

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "좀따할게";
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
