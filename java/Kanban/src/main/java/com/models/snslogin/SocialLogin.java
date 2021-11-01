package com.models.snslogin;

import javax.servlet.http.*;
import java.net.*;
import java.io.*;

import com.core.Logger;
import com.models.member.Member;

/**
 * 소셜 로그인 추상 클래스
 * @author 6-5
 *
 */
public abstract class SocialLogin {
	/**
	 * Access Token을 발급 받기 위한 인증 code 발급 URL 생성
	 * @return
	 */
	public abstract String getCodeURL(HttpServletRequest reqest);
	
	/**
	 * Access Token 발급
	 * 
	 * @param code
	 * @param state
	 * @return
	 */
	public abstract String getAccessToken(String code, String state) 
			throws Exception;
	public abstract String getAccessToken(HttpServletRequest req)
			throws Exception;
	
	/**
	 * 회원 프로필 조회 API를 통해서 각 소셜 채널별 회원 정보 추출 
	 * 
	 * @param accessToken
	 * @return
	 */
	public abstract Member getProfile(String accessToken);
	
	/**
	 * 원격 HTTP 요청...
	 * 
	 * @param apiURL
	 */
	public void httpRequest(String apiURL) {
		try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			
			
		} catch(Exception e) {
			Logger.log(e);
		}
	}
}