package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

import com.snslogin.*;
public class MainController extends HttpServlet{
	private static final long serialVersionUID = -3635061178933968106L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
			res.setContentType("text/html; charset=utf-8");
		
			NaverLogin naver = new NaverLogin(); 
			String naverCodeUrl = naver.getCodeURL(req);
			
			req.setAttribute("naverCodeUrl", naverCodeUrl);
		
			RequestDispatcher rd = req.getRequestDispatcher("/main.jsp");
			rd.include(req, res);
	}
}
