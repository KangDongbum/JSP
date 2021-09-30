package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

import com.model.dao.Member;
import com.model.dto.MemberBean;

public class InsertMember extends HttpServlet {
	private static final long serialVersionUID = 3543676299081502397L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = req.getRequestDispatcher("/insert.jsp");
		rd.forward(req,res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = res.getWriter();
		
		Member member = new Member();
		boolean result = member.insertMember(new MemberBean(req));
		if(result) {
			//메인 페이지로 이동
			res.sendRedirect("main");
			return;
		}else {
			//실패 메세지
			out.print("<script>alert('회원 추가 실패!');history.back();</script>");
		}
	}
}
