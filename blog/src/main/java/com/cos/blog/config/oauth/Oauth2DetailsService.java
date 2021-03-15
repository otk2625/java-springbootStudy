package com.cos.blog.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.blog.config.auth.PricipalDetails;
import com.cos.blog.domain.user.RoleType;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class Oauth2DetailsService extends DefaultOAuth2UserService {
	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("OAuth 로그인 진행중........");
		System.out.println(userRequest.getAccessToken().getTokenValue());
		
		//1. AccessToken으로 회원정보를 받았다는 의미
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		System.out.println(oauth2User.getAttributes());
		
		return processOAuth2User(userRequest, oauth2User);
	}
	
	//구글 로그인 프로세스
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		// userRequest이게 구글인지 페이스북인지 알수있다
		System.out.println("뭘로 로그인? : " + userRequest.getClientRegistration().getClientName());
		OAuth2UserInfo oAuth2UserInfo = null;
		// 1번 통합 클래스를 생성
		if(userRequest.getClientRegistration().getClientName().equals("Google")) {
			oAuth2UserInfo = new GoogleInfo(oAuth2User.getAttributes());
		}else if (userRequest.getClientRegistration().getClientName().equals("Facebook")) {
			oAuth2UserInfo = new FacebookInfo(oAuth2User.getAttributes());
		}else if (userRequest.getClientRegistration().getClientName().equals("Naver")) {
			oAuth2UserInfo = new NaverInfo((Map)oAuth2User.getAttributes().get("response"));
		}else if (userRequest.getClientRegistration().getClientName().equals("Kakao")) {
			oAuth2UserInfo = new KakaoInfo((Map)oAuth2User.getAttributes());
		}
		
		// 2번 최초이면 : 회원가입 + 로그인, 최초가 아닐시 : 로그인만
		User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());
		
		UUID uuid = UUID.randomUUID();
		String encPassword =new BCryptPasswordEncoder() 
				.encode(uuid.toString());
		
		if(userEntity == null) {
			System.out.println("최초 사용자입니다. 자동 회원가입 후 로그인 합니다");
			User user = User.builder()
					.username(oAuth2UserInfo.getUsername())
					.email(oAuth2UserInfo.getEmail())
					.password(encPassword)
					.role(RoleType.USER)
					.build();
			userEntity = userRepository.save(user);
			
			return new PricipalDetails(userEntity, oAuth2User.getAttributes());
		}else { //이미 회원가입이 완료됐다는 뜻(원래는 구글 정보가 변경될 수 있기 때문에 update해야함!)
			//update 로직
			return new PricipalDetails(userEntity, oAuth2User.getAttributes());
		}
		
	}
	
}
