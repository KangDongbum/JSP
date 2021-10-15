package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naverlogin.NaverLogin;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = -7989372756348311667L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = res.getWriter();
		NaverLogin naver = new NaverLogin();
		try {
			String acTK = naver.getAccessToken(req);
			HashMap<String, String> userInfo = naver.getUserProfile(acTK);
			if (userInfo == null) { 
				throw new Exception("유저 정보가 없습니다.");
			} else {
				if (naver.isJoin(userInfo, req)) {
					boolean result = naver.login(req);
					if(!result) {
						throw new Exception("로그인 실패!");
					}else {
						out.print("<script>location.replace('main');</script>");
					}
		
				} else {
					System.out.print(naver.isJoin(userInfo, req));
					out.print("<script>location.replace('member/join');</script>");
				}
				
			}
		
		} catch (Exception e) {
			out.printf("<script>alert('%s');location.href='member/login';</script>", e.getMessage());
		}
	}
}