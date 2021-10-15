package com.naverlogin;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.io.*;
import java.util.*;

import com.exception.*;
import com.models.dto.*;

import org.json.simple.parser.*;
import org.json.simple.*;

public abstract class SocialLogin {

	public abstract void clearSession(HttpServletRequest req);
	public abstract String getCodeURL(HttpServletRequest req);

	public abstract String getAccessToken(HttpServletRequest req) 
			throws AlertException, IOException, ParseException;
	public abstract String getAccessToken(HttpServletRequest req, String code, String state) 
			throws AlertException, IOException, ParseException;

	public abstract HashMap<String, String> getUserProfile(String acTk);

	public abstract boolean isJoin(HashMap<String, String> userInfo, HttpServletRequest req);
	
	public abstract boolean login(HttpServletRequest req);
	
	public abstract Member getSocialUserInfo(HttpServletRequest req);
	
	public JSONObject httpRequest(String apiUrl) throws IOException, ParseException {
		return httpRequest(apiUrl, null);
	}
	
	public JSONObject httpRequest(String apiUrl, HashMap<String,String> headers)
			throws IOException, ParseException {
		URL url = new URL(apiUrl);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		
		if (headers != null) {
			Iterator<String> ir = headers.keySet().iterator();
			while(ir.hasNext()) {
				String key = ir.next();
				String value = headers.get(key);
				conn.setRequestProperty(key, value);
			}
		}
		
		int statusCode = conn.getResponseCode();
	
		InputStream in; 
		if (statusCode == HttpURLConnection.HTTP_OK) {
			in = conn.getInputStream();
		} else {
			in = conn.getErrorStream();
		}
		
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);
		
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		
		br.close();
		isr.close();
		in.close();

		JSONObject json = (JSONObject)new JSONParser().parse(sb.toString());
		
		return json;
	}
}