package com.naverlogin;

import java.util.HashMap;
import java.util.Iterator;
import java.net.URLEncoder;
import java.io.IOException;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.*;
import org.json.simple.*;

import com.core.DB;
import com.exception.*;
import com.models.dto.*;

public class NaverLogin extends SocialLogin {
	
	private static String clientId; 
	private static String clientSecret; 
	private static String callbackUrl; 

	public static void init(String clientId, String clientSecret, String callbackUrl)
			throws Exception {
		NaverLogin.clientId = clientId;
		NaverLogin.clientSecret = clientSecret;
		NaverLogin.callbackUrl = URLEncoder.encode(callbackUrl, "UTF-8");
	}
	
	public static void init(FilterConfig config) 
			throws Exception {
		init(
			config.getInitParameter("NaverClientId"),
			config.getInitParameter("NaverClientSecret"),
			config.getInitParameter("NaverCallbackUrl")
		);
	}
	
	@Override
	public void clearSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("naverUserInfo");
	}
	
	@Override
	public String getCodeURL(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		long state = System.currentTimeMillis();
		session.setAttribute("state", state);
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://nid.naver.com/oauth2.0/authorize?");
		sb.append("response_type=code");
		sb.append("&client_id=");
		sb.append(clientId);
		sb.append("&redirect_uri=");
		sb.append(callbackUrl);
		sb.append("&state=");
		sb.append(state);
		
		return sb.toString();
	}

	@Override
	public String getAccessToken(HttpServletRequest req) 
			throws AlertException, IOException, ParseException {
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		return getAccessToken(req, code, state);
	}

	@Override
	public String getAccessToken(HttpServletRequest req, String code, String state) 
			throws AlertException, IOException, ParseException  {
		HttpSession session = req.getSession();
		String state2 = String.valueOf((Long)session.getAttribute("state"));
		if (!state.equals(state2)) {
			throw new AlertException("데이터가 일치하지 않습니다.");
		}

		StringBuilder sb = new StringBuilder();
		sb.append("https://nid.naver.com/oauth2.0/token?");
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=");
		sb.append(clientId);
		sb.append("&client_secret=");
		sb.append(clientSecret);
		sb.append("&code=");
		sb.append(code);
		sb.append("&state=");
		sb.append(state);
		
		String apiURL = sb.toString();
		JSONObject result = httpRequest(apiURL);
		String acTk = null;
		if (result.containsKey("access_token")) {
			acTk = (String)result.get("access_token");
		}
		
		return acTk;
	}

	@Override
	public HashMap<String, String> getUserProfile(String acTk) {
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + acTk);
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		
		HashMap<String, String> userInfo = null;
		try {
			JSONObject result = httpRequest(apiURL, headers);
			String resultcode = (String)result.get("resultcode");
			if (resultcode.equals("00")) {
				userInfo = new HashMap<String, String>();
				JSONObject response = (JSONObject)result.get("response");
				Iterator<String> ir = response.keySet().iterator();
				while(ir.hasNext()) {
					String key = ir.next();
					String value = (String)response.get(key);
					userInfo.put(key, value);
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}

	@Override
	public boolean isJoin(HashMap<String, String> userInfo, HttpServletRequest req) {
		if (userInfo == null) {
			return false;
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("naverUserInfo", userInfo);
		
		String id = userInfo.get("id");
		String channel = "Naver";
		
		String sql = "SELECT COUNT(*) cnt FROM member WHERE socialChannel = ? AND socialId = ?";
		try (Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, channel);
			pstmt.setString(2, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt > 0) {
					return true;
				}
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean login(HttpServletRequest req) {
		HttpSession session = req.getSession();
		HashMap<String, String> userInfo = (HashMap<String, String>)session.getAttribute("naverUserInfo");
		
		String id = userInfo.get("id");
		String channel = "Naver";
		
		String sql = "SELECT memNo FROM member WHERE socialChannel = ? AND socialId = ?";
		try (Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, channel);
			pstmt.setString(2, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int memNo = rs.getInt("memNo");
				session.setAttribute("memNo", memNo);
				clearSession(req);
				return true;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		clearSession(req);
		return false;
	}
	
	@Override
	public Member getSocialUserInfo(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Member member = null;
		if (session.getAttribute("naverUserInfo") != null) {
			HashMap<String, String> userInfo = (HashMap<String, String>)session.getAttribute("naverUserInfo");
			String memId = null;
			String email = userInfo.get("email");
			if (email != null) {
				memId = email.substring(0, email.lastIndexOf("@"));
			} else {
				memId = String.valueOf(System.currentTimeMillis());
			}
						
			member = new Member(0, memId, null, userInfo.get("name"), "Naver", userInfo.get("id"),null);
		}
		
		return member;
	}
}