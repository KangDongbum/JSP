package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import com.model.dao.Member;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 7227726844899675715L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		
		RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
		rd.forward(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		String memId = req.getParameter("memId");
		String password = req.getParameter("memPw");
		
		Member member = new Member();
		boolean result = member.login(memId, password);
		if(result) { // 로그인 성공시
			out.print("<h1>로그인 성공!</h1>");
		}else { // 로그인 실패시
			out.print("<h1>로그인 실패!</h1>");
		}
	}
}
