package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Blog_dao;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;

public class PostController extends HttpServlet {
	private static final long serialVersionUID = 2304913034010375863L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
			res.setContentType("text/html; charset=utf-8");
			
			req.setAttribute("action", "post");
			
			RequestDispatcher rd = req.getRequestDispatcher("/board/form.jsp");
			rd.include(req, res);
			
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = res.getWriter();
		Blog_dao dao = new Blog_dao();
		int postNm = dao.write(req);
		if(postNm > 0) {
			out.print("<script>parent.location.href='list';</script>");
		} else {
			out.print("<script>alert('게시글 등록 실패!');</script>");
		}
	}
}
