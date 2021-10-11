package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Blog_dao;
import com.dto.Blog;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class EditController extends HttpServlet{

	private static final long serialVersionUID = -4968663991184914247L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		if(req.getParameter("postNm") == null) {
			out.print("<script>alert('잘못된 접근을 하셨습니다.');history.back();</script>");
			return;
		}
		
		int postNm = Integer.parseInt(req.getParameter("postNm")); // 게시글 번호
		Blog_dao dao = new Blog_dao();
		Blog blog = dao.get(postNm);
		if(blog == null) { // 없는 게시글 일때
			out.print("<scrip>alert('없는 게시글 입니다.');history.back();</script>");
			return;
		}
		
		req.setAttribute("blog", blog);
		req.setAttribute("action", "edit");
		
		RequestDispatcher rd = req.getRequestDispatcher("/board/form.jsp");
		rd.include(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		
		PrintWriter out = res.getWriter();
		
		Blog_dao dao = new Blog_dao();
		boolean result = dao.edit(req);
		
		if(result) { // 수정 성공
			out.print("<script>parent.location.href='list';</script>");
		} else { // 수정 실패
			out.print("<script>alert('수정실패!!');</script>");
		}		
	}
}
