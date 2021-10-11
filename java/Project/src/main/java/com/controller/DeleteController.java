package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Blog_dao;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteController extends HttpServlet{
	private static final long serialVersionUID = 2624201499084188760L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		if(req.getParameter("postNm") == null) {
			out.print("<script>alert('잘못된 방식으로 접근하셨습니다.');history.back();</script>");
			return;
		}
		
		Blog_dao dao = new Blog_dao();
		int postNm = Integer.parseInt(req.getParameter("postNm"));
		boolean result = dao.delete(postNm);
		if(result) { // 삭제 성공 -> 목록으로 이동
			res.sendRedirect("list");
		} else { // 삭제 실패 -> 메세지 -> 뒤로가기
			out.print("<script>alert('삭제 실패!');history.back();</script>");
		}
	}
}