package com.controller.member;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 로그인
 * GET - 로그인 양식, POST - 로그인 처리
 *
 */
public class LoginController extends HttpServlet {
	
	/** 로그인 양식 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = req.getRequestDispatcher("/member/login.jsp");
		rd.include(req, res);
	}
	
	/** 로그인 처리 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		
	}
}
