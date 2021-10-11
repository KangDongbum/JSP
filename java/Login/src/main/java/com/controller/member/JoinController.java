package com.controller.member;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.models.dao.MemberDAO;
import com.exception.AlertException;

/**
 * 회원 가입
 * GET - 가입 양식, POST - 가입 처리
 */
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 8620466051006059924L;

	/** 회원 가입 양식 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = req.getRequestDispatcher("/member/form.jsp");
		rd.include(req, res);
	}
	
	/** 회원 가입 처리 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		try {
			MemberDAO dao = new MemberDAO();
			dao.join(req);
		} catch (AlertException e){
			out.print("<script>alert('"+e.getMessage()+"');</script>");
			return;
		}
	}
}