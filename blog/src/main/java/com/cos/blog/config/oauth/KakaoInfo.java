package com.cos.blog.config.oauth;

import java.util.Map;

public class KakaoInfo extends OAuth2UserInfo{

	public KakaoInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		System.out.println("id 값은 : " + attributes.get("id"));
		return (String)attributes.get("id");
	}

	@Override
	public String getName() {
		Map<String , Object> map = (Map)attributes.get("profile");
		return (String)map.get("nickname");
	}

	@Override
	public String getEmail() {
		Map<String, Object> temp = (Map)attributes.get("kakao_account");
		return (String)temp.get("email");
	}

	@Override
	public String getImageUrl() {
		return "";
	}

	@Override
	public String getUsername() {
		return "Kakao_"+attributes.get("id").toString();
	}
}
