package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet{
	private static final long serialVersionUID = 7369716713252621703L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		
		RequestDispatcher rd = req.getRequestDispatcher("/board/main.jsp");
		rd.include(req, res);
	}
}
