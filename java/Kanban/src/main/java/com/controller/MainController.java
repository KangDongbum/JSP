package com.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import com.models.snslogin.*;

/**
 * 메인 페이지 - index.jsp
 *
 */
public class MainController extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		
		String naverCodeURL = NaverLogin.getInstance().getCodeURL(req);
		req.setAttribute("naverCodeURL", naverCodeURL);
		
		RequestDispatcher rd = req.getRequestDispatcher("/view/main/index.jsp");
		rd.include(req,res);
	}
}
