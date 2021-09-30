package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

import com.model.dao.Member;
import com.model.dto.MemberBean;
import java.util.ArrayList;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = -3635061178933968106L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		
		Member member = new Member();
		ArrayList<MemberBean> list = member.getMember();
		req.setAttribute("list", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("/main.jsp");
		rd.forward(req,res);
	}
}