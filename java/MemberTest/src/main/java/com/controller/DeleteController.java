package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.model.dao.Member;

public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 7228733243583594868L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out =res.getWriter();
		Member member = new Member();
		String memId = req.getParameter("memId");
		
		boolean result = member.deleteMember(memId);
		if(result) { // 삭제 성공 -> 메인 /member/main
			res.sendRedirect("main");
			return;
		} else { // 삭제실패 -> 메시지
			out.print("<script>alert('삭제 실패!');history.back();</script>");
		}
	}
}
