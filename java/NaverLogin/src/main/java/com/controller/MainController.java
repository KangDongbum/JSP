package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.naverlogin.NaverLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = -3635061178933968106L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		NaverLogin naver = new NaverLogin();
		String naverUrl = naver.getCodeURL(req);
		req.setAttribute("naverURL", naverUrl);
		
		RequestDispatcher rd = req.getRequestDispatcher("/main.jsp");
		rd.include(req, res);
	}
}
